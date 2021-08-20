package com.seoultech.fooddeuk.signIn

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityUserTypeSelectBinding

class UserTypeSelectActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeSelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserTypeSelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toggleUserTypeButtonStyle()
    }

    private fun toggleUserTypeButtonStyle() {
        binding.userTypeCeoButton.setOnClickListener {
            binding.userTypeCeoButton.setBackgroundColor(ContextCompat.getColor(this,
                R.color.food_deuk_main_color
            ))
            binding.userTypeCustomerButton.setBackgroundColor(ContextCompat.getColor(this,
                R.color.food_deuk_box_light_gray
            ))
            binding.userTypeCeoButton.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.userTypeCustomerButton.setTextColor(ContextCompat.getColor(this,
                R.color.food_deuk_text_b
            ))
        }
        binding.userTypeCustomerButton.setOnClickListener {
            binding.userTypeCeoButton.setBackgroundColor(ContextCompat.getColor(this,
                R.color.food_deuk_box_light_gray
            ))
            binding.userTypeCustomerButton.setBackgroundColor(ContextCompat.getColor(this,
                R.color.food_deuk_main_color
            ))
            binding.userTypeCeoButton.setTextColor(ContextCompat.getColor(this,
                R.color.food_deuk_text_b
            ))
            binding.userTypeCustomerButton.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}