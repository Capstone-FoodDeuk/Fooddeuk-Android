package com.seoultech.fooddeuk.model.httpBody

import com.google.gson.annotations.SerializedName

data class CheckReviewResponse (
    @SerializedName("data")
    val checkReviewData: CheckReviewInfo
)

data class CheckReviewInfo (
    val storeId : Int = 0,
    val name: String = "",
    val category: String = "",
    val userCnt: Int = 0,
    val totalSum: Int = 0,
    val taste: TasteInfo = TasteInfo(),
    val quantity: QuantityInfo = QuantityInfo(),
    val kind: KindInfo = KindInfo()
)

data class TasteInfo (
    val Good: Int = 0,
    val SoSo: Int = 0,
    val Bad: Int = 0
)

data class QuantityInfo (
    val Enough: Int = 0,
    val SoSo: Int = 0,
    val Bad: Int = 0
)

data class KindInfo (
    val Kind: Int = 0,
    val SoSo: Int = 0,
    val Bad: Int = 0
)