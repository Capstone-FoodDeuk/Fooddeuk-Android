package com.seoultech.fooddeuk.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.GuestInfo
import com.seoultech.fooddeuk.model.httpBody.MyPageResponse
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel: ViewModel() {
    val myPageOkCode: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var myPageData: GuestInfo

    fun requestMyPageInfo() {
        FooddeukAPI.requestMyPageInfo().enqueue(object : Callback<MyPageResponse> {
            override fun onResponse(call: Call<MyPageResponse>, response: Response<MyPageResponse>) {
                Log.d("[Fooddeuk API] my page", "마이 페이지 요청 성공")
                myPageData = response.body()?.let {
                    GuestInfo(
                        nickname = it.myPageData.nickname,
                        likes = it.myPageData.likes,
                        reviews = it.myPageData.reviews
                    )
                } ?: GuestInfo()
                myPageOkCode.value = true
            }

            override fun onFailure(call: Call<MyPageResponse>, t: Throwable) {
                Log.d("[Fooddeuk API] my page", "마이 페이지 요청 실패, ${t.message}")
                myPageOkCode.value = false
            }
        })
    }
}