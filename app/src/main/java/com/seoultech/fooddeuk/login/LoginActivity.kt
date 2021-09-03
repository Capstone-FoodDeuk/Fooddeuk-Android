package com.seoultech.fooddeuk.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.seoultech.fooddeuk.MainActivity
import com.seoultech.fooddeuk.ceoOnOff.CeoOnOffActivity
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
        setOnClickLoginButtonListener()
        showSignUpView()
    }

    private fun setLoginIceBreakingImage() {
        when (getUserType()) {
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

    private fun setOnClickLoginButtonListener() {
        binding.btnLogin.setOnClickListener {
            when (getUserType()) {
                UserType.CEO.name -> {
                    val intent = Intent(this, CeoOnOffActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                UserType.CUSTOMER.name -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun getUserType() = intent.getStringExtra("USER_TYPE").toString()
}