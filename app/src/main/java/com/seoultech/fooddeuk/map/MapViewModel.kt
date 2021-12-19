package com.seoultech.fooddeuk.map

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.MapResponse
import com.seoultech.fooddeuk.model.httpBody.TruckInfo
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MapViewModel: ViewModel() {

    private val _homeDataOkCode: MutableLiveData<Boolean> = MutableLiveData()
    val homeDataOkCode: LiveData<Boolean>
        get() = _homeDataOkCode

    private var _truckInfoList: MutableList<TruckInfo>? = mutableListOf()
    val truckInfoList: List<TruckInfo>?
        get() = _truckInfoList

    fun requestHomeData(latitude: Double, longitude: Double, categories: ArrayList<String>?) {
        FooddeukAPI.requestHomeData(latitude, longitude, categories).enqueue(object : Callback<MapResponse> {
            override fun onResponse(call: Call<MapResponse>, response: Response<MapResponse>) {
                Log.d("[Fooddeuk API] home", "지도 정보 요청 성공")
                _truckInfoList = response.body()?.truckList
                _homeDataOkCode.value = true
            }

            override fun onFailure(call: Call<MapResponse>, t: Throwable) {
                Log.d("[Fooddeuk API] home", "지도 정보 요청 실패, ${t.message}")
                _homeDataOkCode.value = false
            }
        })
    }
}