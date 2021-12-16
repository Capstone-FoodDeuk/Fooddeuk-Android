package com.seoultech.fooddeuk.model.httpBody

import java.util.ArrayList

data class OwnerRequest (
    val name: String = "",
    val category: String = "",
    val registerNum: String = "",
    val menus: ArrayList<Menu> = ArrayList<Menu>(),
    val description: String = "",
    val methods: ArrayList<String> = ArrayList<String>()
)

data class Menu(
    val name : String = "",
    val price : Long = 0
)