package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.model.httpBody.LoginRequest
import com.seoultech.fooddeuk.model.httpBody.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FooddeukService {
    // 참고용 1(추후에 지울 것)
    @GET("users/{user}/repos")
    fun getUserRepoList(@Path("user") user: String): Call<List<GitRepoResponse>>

    // 참고용 2(추후에 지울 것)
    @GET("/users/{username}/repos")
    fun getUserRepoInfo(@Path("username") username: String): Call<List<GitRepoNameResopnse>>

    @POST("signup")
    fun requestSignup(@Body signupInfo: SignupRequest): Call<Any>

    @POST("login")
    fun requestLogin(@Body loginInfo: LoginRequest): Call<Any>
}