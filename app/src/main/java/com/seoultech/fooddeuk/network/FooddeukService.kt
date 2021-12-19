package com.seoultech.fooddeuk.network

import com.seoultech.fooddeuk.model.httpBody.*
import retrofit2.Call
import retrofit2.http.*

interface FooddeukService {

    @POST("signup")
    fun requestSignup(@Body signupInfo: SignupRequest): Call<Any>

    @POST("login")
    fun requestLogin(@Body loginInfo: LoginRequest): Call<LoginResponse>

    @POST("owner")
    fun requestOwner(@Body ownerInfo: OwnerRequest): Call<Any>

    @POST("guest/review/{storeId}")
    fun requestStoreReview(@Path("storeId") storeId: Int, @Body reviewInfo: StoreReviewRequest): Call<Any>

    @GET("store/{storeId}")
    fun requestTruckDetailInfo(@Path("storeId") storeId: Int): Call<TruckDetailResponse>

    @POST("store/{storeId}/like")
    fun requestTruckLike(@Path("storeId") storeId: Int): Call<Any>

    @POST("owner/open")
    fun requestTruckOpen(@Body openInfo: OpenInfoRequest): Call<Any>

    @GET("guest")
    fun requestMyPageInfo(): Call<MyPageResponse>
}