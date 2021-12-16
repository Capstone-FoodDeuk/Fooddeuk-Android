package com.seoultech.fooddeuk.review

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityStarReviewBinding


class StarReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarReviewBinding
    lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.ivMypageBack.setOnClickListener {
            finish()
        }

        val storeName = intent.getStringExtra("name").toString()
        val storeCategory = intent.getStringExtra("category")
        val storeId = intent.getIntExtra("storeId", 0)

        binding.tvStore.text = storeName + "의\n전체적인 만족도를 평가해주세요!"

        if (storeCategory == "타코야끼")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_tako)
        else if (storeCategory == "군밤")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_gunbam)
        else if (storeCategory == "고구마")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_goguma)
        else if (storeCategory == "과일")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_apple)
        else if (storeCategory == "붕어빵")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_bungeoppang)
        else if (storeCategory == "분식")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_sundae)

        //다음 화면으로(별점, 카테고리 넣어서 보내기)
        binding.rbStars.setOnTouchListener { v, event ->
            when(event.action){
                MotionEvent.ACTION_UP -> {
                    val numStars : Long = binding.rbStars.getReviewScore().toLong()
                    val intent = Intent(this, DetailReviewActivity::class.java)
                    intent.putExtra("numStars", numStars)
                    intent.putExtra("category", storeCategory)
                    intent.putExtra("id", storeId)
                    startActivity(intent)
                }
            }
            false
        }
    }
}