package com.seoultech.fooddeuk.review

import android.R.id.button1
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityStarReviewBinding
import com.seoultech.fooddeuk.storeSetting.OwnerViewModel


class StarReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStarReviewBinding

    //TODO: input view에서 입력한 가게명과 카테고리 세팅 -> 가게 상세페이지에서 intent로 받아서 할 거임
    val storeName = intent.getStringExtra("name").toString()
    val storeCategory = intent.getStringExtra("category")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStarReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //TODO : 가게 상세페이지 구현 후 back 버튼 누르면 작동하게

        binding.tvStore.text = storeName + "의\n전체적인 만족도를 평가해주세요!"

        if (storeCategory == "타코야끼")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_tako)
        else if (storeCategory == "군밤")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_gunbam)
        else if (storeCategory == "군고구마")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_goguma)
        else if (storeCategory == "과일")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_apple)
        else if (storeCategory == "붕어빵")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_bungeoppang)
        else if (storeCategory == "순대")
            binding.ivFoodTruck.setImageResource(R.drawable.ic_review_bottom_sundae)

        //다음 화면으로(별점, 카테고리 넣어서 보내기)
        binding.ivFoodTruck.setOnClickListener {
            val numStars : Long = binding.rbStars.getReviewScore().toLong()
            val intent = Intent(this, DetailReviewActivity::class.java)
            intent.putExtra("numStars", numStars)
            intent.putExtra("category", storeCategory)
            startActivity(intent)
        }
    }
}