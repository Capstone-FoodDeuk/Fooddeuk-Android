package com.seoultech.fooddeuk.review

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCheckReviewBinding

class CheckReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityCheckReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val tasteGood = intent.getStringExtra("tasteGood")
        val tasteSoSo = intent.getStringExtra("tasteSoSo")
        val tasteBad = intent.getStringExtra("tasteBad")
        val amountGood = intent.getStringExtra("amountGood")
        val amountSoSo = intent.getStringExtra("amountSoSo")
        val amountBad = intent.getStringExtra("amountBad")
        val kindGood = intent.getStringExtra("kindGood")
        val kindSoSo = intent.getStringExtra("kindSoSo")
        val kindBad = intent.getStringExtra("kindBad")

        //TODO:퍼센티지 가져오기


    }
}