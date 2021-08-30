package com.seoultech.fooddeuk.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.MainActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivitySignInBinding
import com.seoultech.fooddeuk.signUp.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLoginIceBreakingImage()
        showSignUpView()
        showTmpButtons()
    }

    private fun setLoginIceBreakingImage() {
        val userType = intent.getStringExtra("USER_TYPE")
        when (userType) {
            UserType.CEO.name -> binding.ivIceBreaking.setImageResource(R.drawable.ic_ceo_login_logo)
            UserType.CUSTOMER.name -> binding.ivIceBreaking.setImageResource(R.drawable.ic_customer_login_logo)
        }
    }

    private fun showSignUpView() {
        binding.tvGoSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }

    // TODO : 기능 덧붙이면서 지워야함
    private fun showTmpButtons() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}