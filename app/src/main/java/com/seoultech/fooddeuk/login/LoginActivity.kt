package com.seoultech.fooddeuk.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.ceoOnOff.CeoOnOffActivity
import com.seoultech.fooddeuk.databinding.ActivitySignInBinding
import com.seoultech.fooddeuk.map.MapActivity
import com.seoultech.fooddeuk.signUp.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setLoginIceBreakingImage()
        setOnClickListeners()
    }

    private fun setLoginIceBreakingImage() {
        when (getUserType()) {
            UserType.CEO.name -> binding.userType = UserType.CEO.name
            UserType.CUSTOMER.name -> binding.userType = UserType.CUSTOMER.name
        }
    }

    private fun setOnClickListeners() {
        binding.btnLogin.setOnClickListener {
            when (getUserType()) {
                UserType.CEO.name -> {
                    val intent = Intent(this, CeoOnOffActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                UserType.CUSTOMER.name -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        binding.tvGoSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java).apply {
                putExtra("USER_TYPE", getUserType())
            }
            startActivity(intent)
        }

        binding.tvFindIdPassword.setOnClickListener {
            val intent = Intent(this, FindIdPwActivity::class.java)
            startActivity(intent)
        }

        binding.ivBackArrow.setOnClickListener {
            finish()
        }
    }

    private fun getUserType() = intent.getStringExtra("USER_TYPE")
}