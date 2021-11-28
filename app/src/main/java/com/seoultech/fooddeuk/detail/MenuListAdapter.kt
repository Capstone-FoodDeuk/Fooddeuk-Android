package com.seoultech.fooddeuk.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.databinding.ItemMenuListBinding
import com.seoultech.fooddeuk.model.httpBody.Menu

class MenuListAdapter : RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {

    private var dataSet: ArrayList<Menu> = arrayListOf()

    class MenuListViewHolder(binding: ItemMenuListBinding) : RecyclerView.ViewHolder(binding.root) {
        private var menuName: TextView
        private var menuPrice: TextView

        init {
            menuName = binding.tvMenuDetail
            menuPrice = binding.tvMenuPrice
        }

        fun bind(dataSet: Menu) {
            menuName.text = dataSet.name
            menuPrice.text = "${dataSet.price} 원"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        val itemViewBinding = ItemMenuListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuListViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) = holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    fun setDataSet(dataSet: ArrayList<Menu>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
    }
}