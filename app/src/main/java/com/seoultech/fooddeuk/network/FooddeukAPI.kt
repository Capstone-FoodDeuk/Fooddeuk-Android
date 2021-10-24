package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import retrofit2.Call
import retrofit2.http.Path

object FooddeukAPI {
    fun getUserRepoList(user: String): Call<List<GitRepoResponse>> {
        return FooddeukServiceImpl.service.getUserRepoList(user)
    }

    fun getUserRepoInfo(username: String): Call<List<GitRepoNameResopnse>> {
        // 실제 내용 적기
        return FooddeukServiceImpl.service.getUserRepoInfo(username)
    }
}