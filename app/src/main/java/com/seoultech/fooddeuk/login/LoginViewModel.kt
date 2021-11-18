package com.seoultech.fooddeuk.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.LoginRequest
import com.seoultech.fooddeuk.model.httpBody.LoginResponse
import com.seoultech.fooddeuk.network.FooddeukAPI
import com.seoultech.fooddeuk.util.FooddeukErrorManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val loginOkCode: MutableLiveData<Boolean> = MutableLiveData()
    var token: String = ""

    fun requestLogin(loginInfo: LoginRequest) {
        FooddeukAPI.requestLogin(loginInfo).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.d("[Fooddeuk API] login", "로그인 요청 성공")
                loginOkCode.postValue(true)
                token = response.body()?.token ?: FooddeukErrorManager.HAVE_NOT_TOKEN.name
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("[Fooddeuk API] login", "로그인 요청 실패 Throwable -> ${t.message}")
                loginOkCode.postValue(false)
            }
        })
    }
}