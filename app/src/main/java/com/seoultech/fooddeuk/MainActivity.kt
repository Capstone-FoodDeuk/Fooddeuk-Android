package com.seoultech.fooddeuk

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.databinding.ActivityMainBinding
import com.seoultech.fooddeuk.detail.TruckDetailActivity
import com.seoultech.fooddeuk.intro.IntroCEOActivity
import com.seoultech.fooddeuk.intro.IntroCustomerActivity
import com.seoultech.fooddeuk.map.MapActivity
import com.seoultech.fooddeuk.mypage.MyPageActivity
import com.seoultech.fooddeuk.review.InputReviewActivity
import com.seoultech.fooddeuk.review.InputStoreActivity
import com.seoultech.fooddeuk.storeSetting.StoreSettingActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        tmpButtonsClickListener() // TODO : 후에 점점 기능들 붙여지면 지울 것
    }

    private fun tmpButtonsClickListener() {
        binding.tmpWriteReview.setOnClickListener {
            val intent = Intent(this, InputStoreActivity::class.java)
            startActivity(intent)
        }
        binding.tmpCheckReview.setOnClickListener {
            val intent = Intent(this, InputReviewActivity::class.java)
            startActivity(intent)
        }
        binding.tmpSettingStore.setOnClickListener {
            val intent = Intent(this, StoreSettingActivity::class.java)
            startActivity(intent)
        }
        binding.tmpMyPage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}