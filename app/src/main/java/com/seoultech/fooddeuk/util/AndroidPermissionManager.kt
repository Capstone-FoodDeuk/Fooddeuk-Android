package com.seoultech.fooddeuk.util

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/**
 * Android 권한 요청
 */
object AndroidPermissionManager {
    private lateinit var permissions: Array<String>
    private const val LOCATION_PERMISSIONS_REQUEST_CODE = 100

    private fun getNeedPermissionRequests(context: Context): ArrayList<String> {
        val needRequests = arrayListOf<String>()
        permissions.forEachIndexed { index, permission ->
            // 사용자가 이미 특정 권한을 허용했는지에 대한 여부
            val isAlreadyAllowed = ContextCompat.checkSelfPermission(context, permission)
            if (isAlreadyAllowed != PackageManager.PERMISSION_GRANTED) {
                needRequests.add(permission)
            }
        }
        return needRequests
    }

    fun request(activity: Activity, context: Context, permissions: Array<String>) {
        this.permissions = permissions
        val needPermissionRequest = getNeedPermissionRequests(context).toArray()
        if (needPermissionRequest.isEmpty()) {
            return // 이전에 이미 사용자가 필요한 권한을 모두 허용한 경우
        }

        ActivityCompat.requestPermissions(activity, permissions, LOCATION_PERMISSIONS_REQUEST_CODE)
    }
}