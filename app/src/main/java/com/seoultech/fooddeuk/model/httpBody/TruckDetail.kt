package com.seoultech.fooddeuk.model.httpBody

import com.google.gson.annotations.SerializedName

data class TruckDetailResponse (
    @SerializedName("data")
    val truckDetailData: TruckDetailData
)

data class TruckDetailData (
    val name: String = "",
    val category: String = "",
    val phoneNumber: String = "",
    val location: Location = Location(),
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val closeTime: String ="",
    val liked: Boolean = false,
    val rating: RatingInfo = RatingInfo(),
    val menuList: ArrayList<Menu> = arrayListOf(),
    val paymentMethods: ArrayList<String> = arrayListOf()
)

data class Location (
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

data class RatingInfo (
    val userCnt: Int = 0,
    val totalSum: Int = 0,
    val taste: ArrayList<ReviewResponse>? = arrayListOf(),
    val quantity: ArrayList<ReviewResponse>? = arrayListOf(),
    val kindness: ArrayList<ReviewResponse>? = arrayListOf()
)


