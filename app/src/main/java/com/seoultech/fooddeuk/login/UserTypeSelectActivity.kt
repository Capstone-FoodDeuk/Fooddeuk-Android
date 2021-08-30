package com.seoultech.fooddeuk.login

import android.content.Intent
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

        setUserTypeButtonToggleStyle()
    }

    private fun setUserTypeButtonToggleStyle() {
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
            showCeoLoginView()
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
            showCustomerLoginView()
        }
    }

    private fun showCeoLoginView() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("USER_TYPE", UserType.CEO.name)
        }
        startActivity(intent)
    }

    private fun showCustomerLoginView() {
        val intent = Intent(this, LoginActivity::class.java).apply {
            putExtra("USER_TYPE", UserType.CUSTOMER.name)
        }
        startActivity(intent)
    }
}