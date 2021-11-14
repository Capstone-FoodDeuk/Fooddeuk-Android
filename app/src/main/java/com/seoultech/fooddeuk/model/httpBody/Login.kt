package com.seoultech.fooddeuk.model.httpBody

data class LoginRequest(
    val loginId: String = "",
    val password: String = ""
)