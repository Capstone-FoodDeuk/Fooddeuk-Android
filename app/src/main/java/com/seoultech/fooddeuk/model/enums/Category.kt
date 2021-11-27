package com.seoultech.fooddeuk.model.enums

enum class Category(val serverName: String, val koreanName: String) {
    TAKOYAKI("Takoyaki", "타코야끼"),
    FISHBREAD("FishBread", "붕어빵"),
    FRUIT("Fruit", "과일"),
    CHESTNUTS("Chestnuts", "군밤"),
    UNDEFINED("", "카테고리")
}