package com.seoultech.fooddeuk.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityInputStoreBinding

class InputStoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInputStoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityInputStoreBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var storeName  = binding.etInputStore.text
        var storeCategory : String = ""

        binding.rgInputCategory.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rbInputTaco -> storeCategory = "타코야끼"
                R.id.rbInputGunbam -> storeCategory = "군밤"
                R.id.rbInputGoguma -> storeCategory = "군고구마"
                R.id.rbInputApple -> storeCategory = "과일"
                R.id.rbInputBungeo -> storeCategory = "붕어빵"
                R.id.rbInputSundae -> storeCategory = "순대"
            }
        }

        binding.btnInputSelect.setOnClickListener {
            val intent = Intent(this, StarReviewActivity::class.java)
            intent.putExtra("name", storeName.toString())
            intent.putExtra("category", storeCategory)
            Log.i("tag", storeCategory)
            startActivity(intent)
        }
    }
}