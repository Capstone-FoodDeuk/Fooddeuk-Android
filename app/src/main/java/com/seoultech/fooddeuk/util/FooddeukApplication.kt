package com.seoultech.fooddeuk.util

import android.app.Application

class FooddeukApplication: Application() {

    companion object {
        lateinit var fooddeukSharedPreference: FooddeukSharedPreference
    }

    override fun onCreate() {
        fooddeukSharedPreference = FooddeukSharedPreference(applicationContext)
        super.onCreate()
    }
}