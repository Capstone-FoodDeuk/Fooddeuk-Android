package com.seoultech.fooddeuk.review

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityStarReviewBinding


class StarReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //TODO : 가게 상세페이지 구현 후 back 버튼 누르면 작동하게

        //input view에서 입력한 가게명과 카테고리 세팅
        val intent = intent
        val storeName = intent.getStringExtra("name").toString()
        val storeCategory = intent.getStringExtra("category")

        binding.tvStore.text = storeName + "의\n전체적인 만족도를 평가해주세요!"

        if (storeCategory == "타코야끼")
            binding.ivFoodTruck.setImageResource(R.drawable.takoyaki_truck)
        else if (storeCategory == "군밤")
            binding.ivFoodTruck.setImageResource(R.drawable.gunbam_truck)
        else if (storeCategory == "군고구마")
            binding.ivFoodTruck.setImageResource(R.drawable.goguma_truck)
        else if (storeCategory == "과일")
            binding.ivFoodTruck.setImageResource(R.drawable.apple_truck)
        else if (storeCategory == "붕어빵")
            binding.ivFoodTruck.setImageResource(R.drawable.bungeo_truck)
        else if (storeCategory == "순대")
            binding.ivFoodTruck.setImageResource(R.drawable.sundae_truck)

        //별점 선택시 다음 화면으로
        binding.rbStars.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            val intent = Intent(this, DetailReviewActivity::class.java)
            val numStars : Float = binding.rbStars.rating
            intent.putExtra("numStars", numStars)
            intent.putExtra("category", storeCategory)
            startActivity(intent)
        }
    }
}