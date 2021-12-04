package com.seoultech.fooddeuk.model.httpBody

import java.time.LocalDateTime

data class OpenInfoRequest(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val closeTime: String = LocalDateTime.now().toString()
)