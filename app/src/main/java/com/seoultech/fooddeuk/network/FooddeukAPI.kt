package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.model.httpBody.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Path

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
}