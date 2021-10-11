package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FooddeukService {
    @GET("users/{user}/repos")
    fun getUserRepoList( @Path("user") user: String): Call<List<GitRepoResponse>>
}