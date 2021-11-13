package com.seoultech.fooddeuk.model.httpBody

data class SignupRequest (
    val role: String = "",
    val loginId: String = "",
    val pwd: String = "",
    val pwdCheck: String = "",
    val nickname: String = "",
    val phoneNumber: String = ""
)