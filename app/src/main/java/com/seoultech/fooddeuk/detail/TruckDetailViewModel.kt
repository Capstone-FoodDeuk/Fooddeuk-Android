package com.seoultech.fooddeuk.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.RatingInfo
import com.seoultech.fooddeuk.model.httpBody.TruckDetailData
import com.seoultech.fooddeuk.model.httpBody.TruckDetailResponse
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TruckDetailViewModel : ViewModel() {

    val truckDetailInfoOkCode: MutableLiveData<Boolean> = MutableLiveData()
    val truckLikeOkCode: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var truckDetailData: TruckDetailData

    fun requestTruckDetailInfo(storeId: Int) {
        FooddeukAPI.requestTruckDetailInfo(storeId).enqueue(object : Callback<TruckDetailResponse> {
            override fun onResponse(call: Call<TruckDetailResponse>, response: Response<TruckDetailResponse>) {
                Log.d("[Fooddeuk API] truck detail", "트럭 상세 정보 요청 성공")
                truckDetailData = response.body()?.let {
                    TruckDetailData(
                        name = it.truckDetailData.name,
                        category = it.truckDetailData.category,
                        phoneNumber = it.truckDetailData.phoneNumber,
                        location = it.truckDetailData.location,
                        closeTime = it.truckDetailData.closeTime,
                        liked = it.truckDetailData.liked,
                        rating = RatingInfo (
                            userCnt = it.truckDetailData.rating.userCnt,
                            totalSum = it.truckDetailData.rating.totalSum,
                            taste = it.truckDetailData.rating.taste,
                            quantity = it.truckDetailData.rating.quantity,
                            kindness = it.truckDetailData.rating.kindness
                        ),
                        menuList = it.truckDetailData.menuList,
                        paymentMethods = it.truckDetailData.paymentMethods
                    )
                } ?: TruckDetailData()
                truckDetailInfoOkCode.value = true
            }

            override fun onFailure(call: Call<TruckDetailResponse>, t: Throwable) {
                Log.d("[Fooddeuk API] truck detail", "트럭 상세 정보 요청 실패, ${t.message}")
                truckDetailInfoOkCode.value = false
            }
        })
    }

    fun requestTruckLike(storeId: Int) {
        FooddeukAPI.requestTruckLike(storeId).enqueue(object : Callback<Any>{
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                Log.d("[Fooddeuk API] truck like", "트럭 찜 요청 성공")
                truckLikeOkCode.value = true
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                Log.d("[Fooddeuk API] truck like", "트럭 찜 요청 실패, ${t.message}")
                truckLikeOkCode.value = false
            }
        })
    }
}