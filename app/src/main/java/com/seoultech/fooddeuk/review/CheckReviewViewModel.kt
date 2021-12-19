package com.seoultech.fooddeuk.review

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.seoultech.fooddeuk.model.httpBody.*
import com.seoultech.fooddeuk.network.FooddeukAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CheckReviewViewModel: ViewModel() {
    val checkReviewOkCode: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var checkReviewData: CheckReviewInfo

    fun requestCheckReviewInfo() {
        FooddeukAPI.requestCheckReviewInfo().enqueue(object : Callback<CheckReviewResponse> {
            override fun onResponse(call: Call<CheckReviewResponse>, response: Response<CheckReviewResponse>) {
                checkReviewData = response.body()?.let {
                    CheckReviewInfo(
                        storeId = it.checkReviewData.storeId,
                        name = it.checkReviewData.name,
                        category = it.checkReviewData.category,
                        userCnt = it.checkReviewData.userCnt,
                        totalSum = it.checkReviewData.totalSum,
                        taste = TasteInfo (
                            Good = it.checkReviewData.taste.Good,
                            SoSo = it.checkReviewData.taste.SoSo,
                            Bad = it.checkReviewData.taste.Bad
                        ),
                        quantity = QuantityInfo (
                            Enough = it.checkReviewData.quantity.Enough,
                            SoSo = it.checkReviewData.quantity.SoSo,
                            Bad = it.checkReviewData.quantity.Bad
                        ),
                        kind = KindInfo (
                            Kind = it.checkReviewData.kind.Kind,
                            SoSo = it.checkReviewData.kind.SoSo,
                            Bad = it.checkReviewData.kind.Bad
                        )
                    )
                } ?: CheckReviewInfo()
                checkReviewOkCode.value = true
                Log.d("[Fooddeuk API] check review", "리뷰 조회 성공")
            }

            override fun onFailure(call: Call<CheckReviewResponse>, t: Throwable) {
                checkReviewOkCode.value = false
                Log.d("[Fooddeuk API] check review", "리뷰 조회 실패")
            }
        })
    }
}