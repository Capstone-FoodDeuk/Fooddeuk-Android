package com.seoultech.fooddeuk.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R

class ReviewAdapter(val reviewList: ArrayList<ReviewData>) : RecyclerView.Adapter<ReviewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_review_items, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.category.setImageResource(reviewList.get(position).categoryImg)
        holder.storeName.text = reviewList.get(position).storeName
        holder.reviewTasteImg.setImageResource(reviewList.get(position).tasteImg)
        holder.reviewTasteTxt.text = reviewList.get(position).tasteTxt
        holder.reviewAmountImg.setImageResource(reviewList.get(position).amountImg)
        holder.reviewAmountTxt.text = reviewList.get(position).amountTxt
        holder.reviewKindImg.setImageResource(reviewList.get(position).kindImg)
        holder.reviewKindTxt.text = reviewList.get(position).kindTxt
    }

    override fun getItemCount(): Int {
        return reviewList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<ImageView>(R.id.img_category) //카테고리
        val storeName = itemView.findViewById<TextView>(R.id.tv_item_store_name) //가게명
        val reviewTasteImg = itemView.findViewById<ImageView>(R.id.iv_item_review_taste) //맛 리뷰 새
        val reviewTasteTxt = itemView.findViewById<TextView>(R.id.tv_item_review_taste) //맛 리뷰 텍스트
        val reviewAmountImg = itemView.findViewById<ImageView>(R.id.iv_item_review_amount) //양 리뷰 새
        val reviewAmountTxt = itemView.findViewById<TextView>(R.id.tv_item_review_amount) //양 리뷰 텍스트
        val reviewKindImg = itemView.findViewById<ImageView>(R.id.iv_item_review_kind) //친절함 리뷰 새
        val reviewKindTxt = itemView.findViewById<TextView>(R.id.tv_item_review_kind) //친절함 리뷰 텍스트
    }

}