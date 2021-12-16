package com.seoultech.fooddeuk.model.httpBody

import com.google.gson.annotations.SerializedName

data class MyPageResponse (
    @SerializedName("data")
    val myPageData: GuestInfo
)

data class GuestInfo (
    val nickname: String = "",
    val likes: ArrayList<Likes> = arrayListOf(),
    val reviews: ArrayList<Reviews> = arrayListOf()
)

data class Likes (
    val name : String = "",
    val category : String = "",
    val latitude : Double = 0.0,
    val longitude : Double = 0.0,
    val closeTime : String = "",
    val isAlarmActive : Boolean = true
)

data class Reviews (
    val name : String = "",
    val category : String = "",
    val score : Long = 0,
    val taste : String = "",
    val quantity : String = "",
    val kindness : String = ""
)