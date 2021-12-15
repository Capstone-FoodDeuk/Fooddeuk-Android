package com.seoultech.fooddeuk.mypage

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

            when (it.category) {
                "Takoyaki" -> imgCategory = R.drawable.ic_category_tako
                "FishBread" -> imgCategory = R.drawable.ic_category_bungeo
                "Snack" -> imgCategory = R.drawable.ic_category_sundae
                "Fruit" -> imgCategory = R.drawable.ic_category_apple
                "Chestnuts" -> imgCategory = R.drawable.ic_category_gunbam
                "Goguma" -> imgCategory = R.drawable.ic_category_goguma
            }

            when (it.isAlarmActive) {
                true -> isAlarm = R.drawable.ic_alarm_on
                false -> isAlarm = R.drawable.ic_alarm_off
            }

            //TODO : 장소랑 시간 설정
            DibsList.add(DibsData(imgCategory, it.name, it.longitude.toString(), it.closeTime, isAlarm))
        }
        mAdapter!!.setData(DibsList)
    }

    private fun callDibsListAPI() = dibsViewModel.requestMyPageInfo()
}