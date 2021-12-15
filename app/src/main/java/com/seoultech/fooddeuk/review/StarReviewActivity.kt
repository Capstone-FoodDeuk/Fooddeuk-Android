package com.seoultech.fooddeuk.review

import android.R.id.button1
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityStarReviewBinding
import com.seoultech.fooddeuk.storeSetting.OwnerViewModel


class StarReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarReviewBinding

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

        if (storeCategory == "Takoyaki")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_tako)
        else if (storeCategory == "Gunbam")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_gunbam)
        else if (storeCategory == "Goguma")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_goguma)
        else if (storeCategory == "Apple")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_apple)
        else if (storeCategory == "Bungeoppang")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_bungeoppang)
        else if (storeCategory == "Sundae")
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