package com.seoultech.fooddeuk.util

import android.content.Context

/**
 * SharedPreference에 데이터 저장/읽기 관리하는 클래스
 */
class FooddeukSharedPreference(context: Context) {
    private val sharedPreference = context.getSharedPreferences("fooddeuk_shared_preference", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreference.edit()
        editor.apply{
            putString("token", token)
            apply()
        }
    }

    fun getToken(): String {
        return sharedPreference.getString("token", FooddeukErrorManager.HAVE_NOT_TOKEN.name) ?: FooddeukErrorManager.HAVE_NOT_TOKEN.name
    }
}