package com.seoultech.fooddeuk.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityInputReviewBinding

class InputReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityInputReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnInputOk.setOnClickListener {
            val intent = Intent(this, CheckReviewActivity::class.java)
            intent.putExtra("tasteGood", binding.etTasteGood.text)
            intent.putExtra("tasteSoSo", binding.etTasteGood.text)
            intent.putExtra("tasteBad", binding.etTasteGood.text)
            intent.putExtra("amountGood", binding.etTasteGood.text)
            intent.putExtra("amountSoSo", binding.etTasteGood.text)
            intent.putExtra("amountBad", binding.etTasteGood.text)
            intent.putExtra("kindGood", binding.etTasteGood.text)
            intent.putExtra("kindSoSo", binding.etTasteGood.text)
            intent.putExtra("kindBad", binding.etTasteGood.text)
            startActivity(intent)
        }
    }
}