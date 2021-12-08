package com.seoultech.fooddeuk.model.httpBody

import java.util.ArrayList

data class StoreReviewRequest (
    val score : Long,
    val taste : String = "",
    val quantity : String = "",
    val kindness : String = ""
)