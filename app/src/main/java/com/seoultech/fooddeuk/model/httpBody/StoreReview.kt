package com.seoultech.fooddeuk.model.httpBody


data class StoreReviewRequest (
    val score : Long = 0,
    val taste : String = "",
    val quantity : String = "",
    val kindness : String = ""
)