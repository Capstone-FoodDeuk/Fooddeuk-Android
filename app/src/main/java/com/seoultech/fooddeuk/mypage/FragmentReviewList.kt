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


class FragmentReviewList : Fragment() {

    private var ReviewList: ArrayList<ReviewData> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var mAdapter: ReviewAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.fragment_review_list, container, false)

        recyclerView = v.findViewById<View>(R.id.rv_review) as RecyclerView
        recyclerView!!.hasFixedSize()
        mAdapter = ReviewAdapter(ReviewList)

        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(activity)
        recyclerView!!.layoutManager = mLayoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = mAdapter

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        prepareData()
    }

    private fun prepareData() {
        ReviewList.add(ReviewData(R.drawable.ic_category_bungeo, "붕어집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_tako, "타코집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_apple, "과일집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_gunbam, "군밤집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_sundae, "순대집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_goguma, "고구마", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_bungeo, "붕어집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_tako, "타코집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_apple, "과일집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_gunbam, "군밤집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_sundae, "순대집", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
        ReviewList.add(ReviewData(R.drawable.ic_category_goguma, "고구마", R.drawable.ic_emoji_good, "맛있어요!", R.drawable.ic_emoji_bad,"부족해요", R.drawable.ic_emoji_soso, "보통이에요"))
    }

}