package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoResponse
import retrofit2.Call

object FooddeukAPI {
    fun getUserRepoList(user: String): Call<List<GitRepoResponse>> {
        return FooddeukServiceImpl.service.getUserRepoList(user)
    }
}