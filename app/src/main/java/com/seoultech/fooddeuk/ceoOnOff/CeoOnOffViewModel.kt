package com.seoultech.fooddeuk.ceoOnOff

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.OpenInfoRequest
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CeoOnOffViewModel : ViewModel() {

    val truckOpenOkCode: MutableLiveData<Boolean> = MutableLiveData()
    var currentLatitude: Double = 0.0
    var currentLongitude: Double = 0.0

    fun requestTruckOpen(openInfo: OpenInfoRequest) {
        FooddeukAPI.requestTruckOpen(openInfo).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("[Fooddeuk API] truck open", "트럭 오픈 요청 성공")
                truckOpenOkCode.value = true
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("[Fooddeuk API] truck open", "트럭 오픈 요청 실패, ${t.message}")
                truckOpenOkCode.value = false
            }
        })
    }
}