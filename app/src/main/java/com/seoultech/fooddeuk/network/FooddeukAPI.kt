package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.model.httpBody.*
import retrofit2.Call
import retrofit2.http.Body

object FooddeukAPI {
    // 참고용 1 (추후에 지울 것)
    fun getUserRepoList(user: String): Call<List<GitRepoResponse>> {
        return FooddeukServiceImpl.service.getUserRepoList(user)
    }

    // 참고용 2 (추후에 지울 것)
    fun getUserRepoInfo(username: String): Call<List<GitRepoNameResopnse>> {
        return FooddeukServiceImpl.service.getUserRepoInfo(username)
    }

    fun requestSignup(signupInfo: SignupRequest): Call<Any> {
        return FooddeukServiceImpl.service.requestSignup(signupInfo)
    }

    fun requestLogin(loginInfo: LoginRequest): Call<LoginResponse> {
        return FooddeukServiceImpl.service.requestLogin(loginInfo)
    }

    fun requestOwner(ownerInfo: OwnerRequest) : Call<Any> {
        return FooddeukServiceImpl.service.requestOwner(ownerInfo)
    }

    fun requestStoreReview(storeId: Int) : Call<Any> {
        return FooddeukServiceImpl.service.requestStoreReview(storeId)
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