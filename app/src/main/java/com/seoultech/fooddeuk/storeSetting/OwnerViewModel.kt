package com.seoultech.fooddeuk.storeSetting

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.OwnerRequest
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OwnerViewModel : ViewModel() {

    val ownerOkCode: MutableLiveData<Boolean> = MutableLiveData()

    fun requestOwner(ownerInfo: OwnerRequest) {
        FooddeukAPI.requestOwner(ownerInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                ownerOkCode.postValue(true)
                Log.d("[Fooddeuk API] owner", "가게 정보 설정 성공")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                ownerOkCode.postValue(false)
                Log.d("[Fooddeuk API] owner", "가게 정보 설정 실패 Throwable -> ${t.message}")
            }
        })
    }
}