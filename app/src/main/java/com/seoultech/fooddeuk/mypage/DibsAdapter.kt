package com.seoultech.fooddeuk.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R

class DibsAdapter() : RecyclerView.Adapter<DibsAdapter.CustomViewHolder>() {

    var dibsList: ArrayList<DibsData> = arrayListOf()

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<ImageView>(R.id.img_category) //카테고리
        val storeName = itemView.findViewById<TextView>(R.id.tv_item_store_name) //가게명
        val salesPlace = itemView.findViewById<TextView>(R.id.tv_item_sales_place) //판매 장소
        val timeBefore = itemView.findViewById<TextView>(R.id.tv_item_time_before) //판매 시간
        val storeAlarm = itemView.findViewById<ImageView>(R.id.iv_store_alarm) //가게 알람
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DibsAdapter.CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_dibs_items, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.category.setImageResource(dibsList.get(position).categoryImg)
        holder.storeName.text = dibsList.get(position).storeName
        holder.salesPlace.text = dibsList.get(position).salesPlace
        holder.timeBefore.text = dibsList.get(position).timeBefore
        holder.storeAlarm.setImageResource(dibsList.get(position).storeAlarm)
    }

    override fun getItemCount(): Int {
        return dibsList.size
    }

    fun setData(newData: ArrayList<DibsData>) {
        dibsList = newData
        notifyDataSetChanged()
    }
}