package com.seoultech.fooddeuk.signUp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivitySignUpBinding
import com.seoultech.fooddeuk.intro.IntroCEOActivity
import com.seoultech.fooddeuk.intro.IntroCustomerActivity
import com.seoultech.fooddeuk.login.UserType

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.ivBackArrow.setOnClickListener {
            finish()
        }
        binding.btnSignUpComplete.setOnClickListener {
            when (getUserType()) {
                UserType.CEO.name -> {
                    val intent = Intent(this, IntroCEOActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                UserType.CUSTOMER.name -> {
                    val intent = Intent(this, IntroCustomerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun getUserType() = intent.getStringExtra("USER_TYPE")
}