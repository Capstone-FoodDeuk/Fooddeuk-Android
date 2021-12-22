package com.seoultech.fooddeuk.mypage

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.LayoutRecyclerDibsItemsBinding
import com.seoultech.fooddeuk.model.httpBody.Likes
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


class FragmentDibsList : Fragment() {

    private lateinit var mBinding: LayoutRecyclerDibsItemsBinding

    private var DibsList: ArrayList<DibsData> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: DibsAdapter? = null
    private lateinit var dibsViewModel: MyPageViewModel
    var getDibs: ArrayList<Likes> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_dibs_list, container, false)

        recyclerView = v.findViewById<View>(R.id.rv_dibs) as RecyclerView
        recyclerView!!.hasFixedSize()
        mAdapter = DibsAdapter()

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        mBinding = LayoutRecyclerDibsItemsBinding.inflate(inflater, container, false)

        dibsViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
        subscribeViewModel()

        callDibsListAPI()

        return v
    }

    private fun subscribeViewModel() {
        dibsViewModel.myPageOkCode.observe(this, {
            if (it) {
                val myPageDataLikes = dibsViewModel.myPageData.likes
                for(i in myPageDataLikes){
                    Log.i("[dibs] subscribe view model : store name", i.name)
                    getDibs.add(i)
                }
                prepareData(getDibs)
            } else {
                Toast.makeText(activity, "찜 목록을 가져 오는 데 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun prepareData(list: List<Likes>) {
        list.forEach {
            var imgCategory : Int = 0
            var isAlarm : Int = 0
            var timeBefore : String = ""
            var address : String = ""

            // 카테고리
            when (it.category) {
                "Takoyaki" -> imgCategory = R.drawable.ic_category_tako
                "FishBread" -> imgCategory = R.drawable.ic_category_bungeo
                "Snack" -> imgCategory = R.drawable.ic_category_sundae
                "Fruit" -> imgCategory = R.drawable.ic_category_apple
                "Chestnuts" -> imgCategory = R.drawable.ic_category_gunbam
                "GOGUMA" -> imgCategory = R.drawable.ic_category_goguma
            }

            // 알람 설정
            when (it.isAlarmActive) {
                true -> isAlarm = R.drawable.ic_alarm_on
                false -> isAlarm = R.drawable.ic_alarm_off
            }

            //TODO : 장소 설정
            address = getAddress(it.location.latitude, it.location.longitude)

            // 시간 설정
            timeBefore = getBeforeTime(it.closeTime)

            DibsList.add(DibsData(imgCategory, it.name, address, timeBefore, isAlarm))
        }
        mAdapter!!.setData(DibsList)
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        var geocoder = Geocoder(this.context)
        var list: List<Address> = geocoder.getFromLocation(latitude, longitude, 2)

        if(list!=null) {
            if (list.size == 0) {
                Log.e("Reverse GeoCoding", "주소값이 없어요")
                return ""
            }
            else {
                var address = list.get(0).getAddressLine(0).toString()
                for (i in 1..3) {
                    address = address.substring(address.indexOf(" ")+1)
                }
                return address
            }
        }
        return ""
    }

    private fun getBeforeTime(closeTime: String): String {
        var closeTime : LocalDateTime = LocalDateTime.parse(closeTime)
        var now : LocalDateTime = LocalDateTime.now()
        var duration = ChronoUnit.HOURS.between(closeTime, now).toInt()
        var day = duration/24
        var hour = duration%24

        if(day==0 && hour<=0)
            return "영업 중"
        else if(day==0 && hour>0)
            return hour.toString()+"시간 전"
        else
            return day.toString()+"일 "+hour.toString()+"시간 전"
    }

    private fun callDibsListAPI() = dibsViewModel.requestMyPageInfo()
}