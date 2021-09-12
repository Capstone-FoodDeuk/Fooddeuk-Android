package com.seoultech.fooddeuk.map

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.seoultech.fooddeuk.R

class CategoryFilterRecyclerviewAdapter : RecyclerView.Adapter<CategoryFilterRecyclerviewAdapter.CategoryFilterRecyclerviewHolder>() {

    private lateinit var dataSet: Array<CategoryFilterData>

    fun setData(dataSet: Array<CategoryFilterData>) {
        this.dataSet = dataSet
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryFilterRecyclerviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_category_filter_item, parent, false)
        return CategoryFilterRecyclerviewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryFilterRecyclerviewHolder, position: Int) = holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size

    inner class CategoryFilterRecyclerviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvCategoryName: TextView = itemView.findViewById(R.id.tv_category_name)
        val ivCategoryLogo: ImageView = itemView.findViewById(R.id.iv_category_logo)

        fun bind(data: CategoryFilterData) {
            ivCategoryLogo.setImageResource(data.img)
            tvCategoryName.text = data.name
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "${data.name} 눌림", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class GridItemDecoration : RecyclerView.ItemDecoration() {
    private var spanCount: Int = 3 // column 수
    private var spacing: Int = 120 // 간격

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount
        if (position < spanCount) {
            outRect.top = spacing // item top
        }
        outRect.bottom = spacing
    }
}

data class CategoryFilterData (
    val img: Int,
    val name: String
)