package com.seoultech.fooddeuk.model.httpBody

import com.google.gson.annotations.SerializedName

data class MapResponse(
    @SerializedName("data")
    val truckList: MutableList<TruckInfo>
)

data class TruckInfo(
    val id: Int,
    val name: String,
    val category: String,
    val latitude: Double,
    val longitude: Double
)