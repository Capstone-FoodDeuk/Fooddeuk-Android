package com.seoultech.fooddeuk.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FooddeukServiceImpl {

    private const val BASE_URL: String = "http://3.34.5.117:8080/api/"

    private val commonNetworkInterceptor = Interceptor { chain ->
        val newRequest = chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(newRequest)
    }

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(commonNetworkInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: FooddeukService = retrofit.create(FooddeukService::class.java)
}

