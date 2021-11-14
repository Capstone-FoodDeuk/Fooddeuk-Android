package com.seoultech.fooddeuk.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.LoginRequest
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {

    val loginOkCode: MutableLiveData<Boolean> = MutableLiveData()

    fun requestLogin(loginInfo: LoginRequest) {
        FooddeukAPI.requestLogin(loginInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                loginOkCode.postValue(true)
                Log.d("[Fooddeuk API] login", "로그인 요청 성공")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                loginOkCode.postValue(false)
                Log.d("[Fooddeuk API] login", "로그인 요청 실패 Throwable -> ${t.message}")
            }
        })
    }
}