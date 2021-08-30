package com.seoultech.fooddeuk.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityTruckDetailBinding

class TruckDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTruckDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruckDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}