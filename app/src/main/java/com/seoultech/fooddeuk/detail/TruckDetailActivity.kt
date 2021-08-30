package com.seoultech.fooddeuk.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityTruckDetailBinding
import com.seoultech.fooddeuk.dialog.NoTruckDialog

class TruckDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTruckDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruckDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.layoutTruckDetailTitle.tvReport.setOnClickListener {
            NoTruckDialog().show(supportFragmentManager, "layout_dialog_no_truck") // 다이얼로그 쇼
        }
    }
}