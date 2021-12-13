package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.GitRepoNameResopnse
import com.seoultech.fooddeuk.model.GitRepoResponse
import com.seoultech.fooddeuk.model.httpBody.*
import retrofit2.Call
import retrofit2.http.*

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
    fun requestLogin(@Body loginInfo: LoginRequest): Call<LoginResponse>

    @POST("owner")
    fun requestOwner(@Body ownerInfo: OwnerRequest): Call<Any>

    @POST("review/{storeId}")
    fun requestStoreReview(@Body storeReviewInfo: StoreReviewRequest): Call<Any>

    @GET("store/{storeId}")
    fun requestTruckDetailInfo(@Path("storeId") storeId: Int): Call<TruckDetailResponse>

    @POST("store/{storeId}/like")
    fun requestTruckLike(@Path("storeId") storeId: Int): Call<Any>

    @POST("owner/open")
    fun requestTruckOpen(@Body openInfo: OpenInfoRequest): Call<Any>

    @GET("guest")
    fun requestMyPageInfo(): Call<MyPageResponse>
}