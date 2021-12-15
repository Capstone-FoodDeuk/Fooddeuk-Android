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
import com.seoultech.fooddeuk.databinding.LayoutRecyclerReviewItemsBinding
import com.seoultech.fooddeuk.model.httpBody.Reviews


class FragmentReviewList : Fragment() {

    private lateinit var mBinding: LayoutRecyclerReviewItemsBinding

    private var ReviewList: ArrayList<ReviewData> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: ReviewAdapter? = null
    private lateinit var reviewViewModel: MyPageViewModel
    var getReviews: ArrayList<Reviews> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_review_list, container, false)

        recyclerView = v.findViewById<View>(R.id.rv_review) as RecyclerView
        recyclerView!!.hasFixedSize()
        mAdapter = ReviewAdapter()

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        mBinding = LayoutRecyclerReviewItemsBinding.inflate(inflater, container, false)

        reviewViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
        subscribeViewModel()

        callReviewListAPI()

        return v
    }

    private fun subscribeViewModel() {
        reviewViewModel.myPageOkCode.observe(this, {
            if (it) {
                val myPageDataLikes = reviewViewModel.myPageData.reviews
                for(i in myPageDataLikes){
                    Log.i("[dibs] subscribe view model : store name", i.name)
                    getReviews.add(i)
                }
                prepareData(getReviews)
            } else {
                Toast.makeText(activity, "리뷰 목록을 가져 오는 데 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun prepareData(list: ArrayList<Reviews>) {
        list.forEach {
            var imgCategory : Int = 0
            var imgTaste : Int = 0
            var imgAmount : Int = 0
            var imgKind : Int = 0

            when (it.category) {
                "Takoyaki" -> imgCategory = R.drawable.ic_category_tako
                "FishBread" -> imgCategory = R.drawable.ic_category_bungeo
                "Snack" -> imgCategory = R.drawable.ic_category_sundae
                "Fruit" -> imgCategory = R.drawable.ic_category_apple
                "Chestnuts" -> imgCategory = R.drawable.ic_category_gunbam
                "Goguma" -> imgCategory = R.drawable.ic_category_goguma
            }

            when (it.taste) {
                "Good" -> imgTaste = R.drawable.ic_emoji_good
                "SoSo" -> imgTaste = R.drawable.ic_emoji_soso
                "Bad" -> imgTaste = R.drawable.ic_emoji_bad
            }

            when (it.quantity) {
                "Good" -> imgAmount = R.drawable.ic_emoji_good
                "SoSo" -> imgAmount = R.drawable.ic_emoji_soso
                "Bad" -> imgAmount = R.drawable.ic_emoji_bad
            }

            when (it.kindness) {
                "Good" -> imgKind = R.drawable.ic_emoji_good
                "SoSo" -> imgKind = R.drawable.ic_emoji_soso
                "Bad" -> imgKind = R.drawable.ic_emoji_bad
            }

            ReviewList.add(ReviewData(imgCategory, it.name, imgTaste, it.taste, imgAmount, it.quantity, imgKind, it.kindness))
            Log.i("[dibs] prepare data : store name", it.name)
        }
        mAdapter!!.setData(ReviewList)
    }

    private fun callReviewListAPI() = reviewViewModel.requestMyPageInfo()
}