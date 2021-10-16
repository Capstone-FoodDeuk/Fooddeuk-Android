package com.seoultech.fooddeuk.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.LayoutRecyclerDibsItemsBinding


class FragmentDibsList : Fragment() {

    private var mBinding: LayoutRecyclerDibsItemsBinding? = null

    private var DibsList: ArrayList<DibsData> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: DibsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_dibs_list, container, false)

        recyclerView = v.findViewById<View>(R.id.rv_dibs) as RecyclerView
        recyclerView!!.hasFixedSize()
        mAdapter = DibsAdapter(DibsList)

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        mBinding = LayoutRecyclerDibsItemsBinding.inflate(inflater, container, false)

        prepareData()

        return v
    }

    private fun prepareData() {
        DibsList.add(DibsData(R.drawable.ic_category_bungeo, "붕어집", "공릉로123", "3시간 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_apple, "과일집", "공릉로456", "2일 전", R.drawable.ic_alarm_off))
        DibsList.add(DibsData(R.drawable.ic_category_gunbam, "군밤집", "공릉로789", "21시간 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_sundae, "순대집", "공릉로135", "5시간 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_tako, "타코집", "공릉로246", "13시간 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_bungeo, "붕어집", "공릉로123", "3시간 전", R.drawable.ic_alarm_off))
        DibsList.add(DibsData(R.drawable.ic_category_apple, "과일집", "공릉로456", "2일 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_gunbam, "군밤집", "공릉로789", "21시간 전", R.drawable.ic_alarm_off))
        DibsList.add(DibsData(R.drawable.ic_category_sundae, "순대집", "공릉로135", "5시간 전", R.drawable.ic_alarm_on))
        DibsList.add(DibsData(R.drawable.ic_category_tako, "타코집", "공릉로246", "13시간 전", R.drawable.ic_alarm_on))
    }

}