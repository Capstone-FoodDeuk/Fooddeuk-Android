package com.seoultech.fooddeuk.storeSetting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R

class MenuAdapter(var items: List<MenuData>?) : RecyclerView.Adapter<MenuAdapter.ViewHolder>(){
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_menu,parent,false)
        mContext = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.menu.text = items!!.get(position).menu
        holder.cost.text = items!!.get(position).cost
        holder.cancel.setImageResource(items!!.get(position).cancel)

        // 삭제만 구현하면 됨!!!!!!
        holder.cancel.setOnClickListener {
            Toast.makeText(mContext, "삭제 눌림", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun getItem(): List<MenuData>?{
        return items
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val menu = itemView.findViewById<TextView>(R.id.tv_menu)
        val cost = itemView.findViewById<TextView>(R.id.tv_cost)
        val cancel = itemView.findViewById<ImageButton>(R.id.btn_cancel)
    }
}