package com.seoultech.fooddeuk.signUp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.SignupRequest
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel: ViewModel() {

    val signupOkStatus: MutableLiveData<Boolean> = MutableLiveData()

    fun requestSignup(signupInfo: SignupRequest) {
       FooddeukAPI.requestSignup(signupInfo).enqueue(object : Callback<Any> {
           override fun onResponse(call: Call<Any>, response: Response<Any>) {
               signupOkStatus.postValue(true)
               Log.d("[Fooddeuk API] signup", "회원가입 요청 성공")
           }

           override fun onFailure(call: Call<Any>, t: Throwable) {
               signupOkStatus.postValue(false)
               Log.d("[Fooddeuk API] signup", "회원가입 요청 실패 Throwable -> ${t.message}")
           }
       })
    }
}