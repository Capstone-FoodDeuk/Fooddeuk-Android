package com.seoultech.fooddeuk.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FooddeukServiceImpl {

    private const val BASE_URL: String = "https://api.github.com/"

    private val commonNetworkInterceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val newRequest = chain.request().newBuilder()
                .addHeader("Accept", "application/vnd.github.v3+json")
                .addHeader("User-Agent", "choheeis")
                .build()
            return chain.proceed(newRequest)
        }
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

