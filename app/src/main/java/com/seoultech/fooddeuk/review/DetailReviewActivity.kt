package com.seoultech.fooddeuk.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityDetailReviewBinding
import com.seoultech.fooddeuk.databinding.ActivityInputStoreBinding

class DetailReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityDetailReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}