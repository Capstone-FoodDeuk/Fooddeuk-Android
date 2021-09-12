package com.seoultech.fooddeuk.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.seoultech.fooddeuk.R

class CategoryFilterBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "CategoryFilterBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.layout_category_filter_bottom_sheet, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryFilterRecyclerview: RecyclerView = view.findViewById(R.id.rv_category_filter)
        categoryFilterRecyclerview.apply {
            layoutManager = GridLayoutManager(view.context, 3)
            addItemDecoration(GridItemDecoration())
        }

        val categoryFilterRecyclerviewAdapter = CategoryFilterRecyclerviewAdapter()
        categoryFilterRecyclerview.adapter = categoryFilterRecyclerviewAdapter

        val categoryList: Array<CategoryFilterData> = arrayOf(
            CategoryFilterData(R.drawable.ic_category_tako, "타고야끼"),
            CategoryFilterData(R.drawable.ic_category_gunbam, "군밤"),
            CategoryFilterData(R.drawable.ic_category_goguma, "군고구마"),
            CategoryFilterData(R.drawable.ic_category_apple, "과일"),
            CategoryFilterData(R.drawable.ic_category_bungeo, "붕어빵"),
            CategoryFilterData(R.drawable.ic_category_sundae, "순대")
        )

        categoryFilterRecyclerviewAdapter.apply {
            setData(categoryList)
            notifyDataSetChanged()
        }
    }
}