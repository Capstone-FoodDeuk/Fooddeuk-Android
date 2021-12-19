package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.httpBody.*
import retrofit2.Call

object FooddeukAPI {

    fun requestSignup(signupInfo: SignupRequest): Call<Any> {
        return FooddeukServiceImpl.service.requestSignup(signupInfo)
    }

    fun requestLogin(loginInfo: LoginRequest): Call<LoginResponse> {
        return FooddeukServiceImpl.service.requestLogin(loginInfo)
    }

    fun requestOwner(ownerInfo: OwnerRequest) : Call<Any> {
        return FooddeukServiceImpl.service.requestOwner(ownerInfo)
    }

    fun requestStoreReview(storeId: Int, reviewInfo: StoreReviewRequest) : Call<Any> {
        return FooddeukServiceImpl.service.requestStoreReview(storeId, reviewInfo)
    }

    fun requestTruckDetailInfo(storeId: Int): Call<TruckDetailResponse> {
        return FooddeukServiceImpl.service.requestTruckDetailInfo(storeId)
    }

    fun requestTruckLike(storeId: Int): Call<Any> {
        return FooddeukServiceImpl.service.requestTruckLike(storeId)
    }

    fun requestTruckOpen(openInfo: OpenInfoRequest): Call<Any> {
        return FooddeukServiceImpl.service.requestTruckOpen(openInfo)
    }

    fun requestMyPageInfo(): Call<MyPageResponse> {
        return FooddeukServiceImpl.service.requestMyPageInfo()
    }
}