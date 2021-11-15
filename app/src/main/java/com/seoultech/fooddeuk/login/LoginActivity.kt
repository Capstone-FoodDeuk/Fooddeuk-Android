package com.seoultech.fooddeuk.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.ceoOnOff.CeoOnOffActivity
import com.seoultech.fooddeuk.databinding.ActivityLoginBinding
import com.seoultech.fooddeuk.map.MapActivity
import com.seoultech.fooddeuk.model.httpBody.LoginRequest
import com.seoultech.fooddeuk.signUp.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // set things
        setContentView(binding.root)
        setLoginIceBreakingImage()
        setOnClickListeners()

        // subscribe viewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        loginViewModel.loginOkCode.observe(this, {
            if (it) {
                goToMainActivity()
            } else {
                Toast.makeText(this, "죄송합니다. 로그인 요청에 실패하여 잠시후 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setLoginIceBreakingImage() {
        when (getUserType()) {
            UserType.CEO.name -> binding.userType = UserType.CEO.name
            UserType.CUSTOMER.name -> binding.userType = UserType.CUSTOMER.name
        }
    }

    private fun setOnClickListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                val loginInfo = getLoginInfoFromView()
                callLoginAPI(loginInfo)
            }
            tvGoSignUp.setOnClickListener {
                val intent = Intent(this@LoginActivity, SignUpActivity::class.java).apply {
                    putExtra("USER_TYPE", getUserType())
                }
                startActivity(intent)
            }
            tvFindIdPassword.setOnClickListener {
                val intent = Intent(this@LoginActivity, FindIdPwActivity::class.java)
                startActivity(intent)
            }
            ivBackArrow.setOnClickListener {
                finish()
            }
        }
    }

    private fun goToMainActivity() {
        when (getUserType()) {
            UserType.CEO.name -> {
                Toast.makeText(this, "사장님 오늘도 응원합니다~", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, CeoOnOffActivity::class.java)
                startActivity(intent)
                finish()
            }
            UserType.CUSTOMER.name -> {
                Toast.makeText(this, "맛있는 간식을 찾아서 푸드득!!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MapActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getLoginInfoFromView(): LoginRequest {
        return LoginRequest(
            loginId = binding.etId.text.toString(),
            password = binding.etPassword.text.toString()
        )
    }

    private fun getUserType() = intent.getStringExtra("USER_TYPE")

    private fun callLoginAPI(loginInfo: LoginRequest) = loginViewModel.requestLogin(loginInfo)
}