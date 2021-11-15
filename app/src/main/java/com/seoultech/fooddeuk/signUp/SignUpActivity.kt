package com.seoultech.fooddeuk.signUp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.databinding.ActivitySignUpBinding
import com.seoultech.fooddeuk.intro.IntroCEOActivity
import com.seoultech.fooddeuk.intro.IntroCustomerActivity
import com.seoultech.fooddeuk.login.UserType
import com.seoultech.fooddeuk.model.httpBody.SignupRequest

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignupViewModel
    private lateinit var userType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        // set things
        setContentView(binding.root)
        setUserType(getUserType())
        setClickListeners()

        // subscribe viewModel
        signUpViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        signUpViewModel.signupOkCode.observe(this, {
            if (it) {
                goToIntroActivity()
                Toast.makeText(this, "감사합니다. 회원 가입 완료되었습니다~", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "죄송합니다. 회원 가입 요청에 실패하여 잠시후 다시 시도해주세요~", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUserType(userType: String?) {
        this.userType = when (userType) {
            UserType.CEO.name -> UserType.CEO.role
            UserType.CUSTOMER.name -> UserType.CUSTOMER.role
            else -> UserType.UNKNOWN.role
        }
    }

    private fun setClickListeners() {
        binding.apply {
            btnSignUpComplete.setOnClickListener {
                val signupInfo = getSignupInfoFromView()
                callSignupAPI(signupInfo)
            }
            ivBackArrow.setOnClickListener { finish() }
        }
    }

    private fun getSignupInfoFromView(): SignupRequest {
        return SignupRequest(
            role = userType,
            loginId = binding.tietId.text.toString(),
            pwd = binding.tietPw.text.toString(),
            pwdCheck = binding.tietPwConfirm.text.toString(),
            nickname = binding.tietNickName.text.toString(),
            phoneNumber = binding.tietPhoneNumber.text.toString()
        )
    }

    private fun goToIntroActivity() {
        when (userType) {
            UserType.CEO.role -> {
                val intent = Intent(this, IntroCEOActivity::class.java)
                startActivity(intent)
                finish()
            }
            UserType.CUSTOMER.role -> {
                val intent = Intent(this, IntroCustomerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun getUserType(): String? = intent.getStringExtra("USER_TYPE")

    private fun callSignupAPI(signupInfo: SignupRequest) = signUpViewModel.requestSignup(signupInfo)
}