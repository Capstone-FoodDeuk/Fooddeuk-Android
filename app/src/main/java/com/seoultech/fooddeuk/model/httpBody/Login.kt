package com.seoultech.fooddeuk.model.httpBody

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    val loginId: String = "",
    val password: String = ""
)

data class LoginResponse(
    @SerializedName("data")
    val token: String = ""
)