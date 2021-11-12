package com.seoultech.fooddeuk.login

enum class UserType(val role: String) {
    CEO("OWNER"),
    CUSTOMER("GUEST"),
    UNKNOWN("UNKNOWN")
}