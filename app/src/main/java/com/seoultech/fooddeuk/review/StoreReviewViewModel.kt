package com.seoultech.fooddeuk.review

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.StoreReviewRequest
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Path

class StoreReviewViewModel : ViewModel() {
    val storeReviewOkCode: MutableLiveData<Boolean> = MutableLiveData()

    fun requestStoreReview(storeId: Int) {
        FooddeukAPI.requestStoreReview(storeId).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                storeReviewOkCode.postValue(true)
                Log.d("[Fooddeuk API] store review", "리뷰 작성 성공")
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                storeReviewOkCode.postValue(false)
                Log.d("[Fooddeuk API] store review", "리뷰 작성 실패 Throwable -> ${t.message}")
            }
        })
    }
}