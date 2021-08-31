package com.seoultech.fooddeuk

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.databinding.ActivitySplashBinding
import com.seoultech.fooddeuk.login.UserTypeSelectActivity
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


class SplashActivity : AppCompatActivity() {

    private lateinit var handler: Handler
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()
        handler.postDelayed({
            showUserTypeSelectView()
        }, 1000)

    }

    private fun showUserTypeSelectView() {
        val intent = Intent(this, UserTypeSelectActivity::class.java)
        startActivity(intent)
        finish()
    }
}