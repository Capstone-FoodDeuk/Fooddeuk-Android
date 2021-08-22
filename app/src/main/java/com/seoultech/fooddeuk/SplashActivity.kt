package com.seoultech.fooddeuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.seoultech.fooddeuk.databinding.ActivitySplashBinding
import com.seoultech.fooddeuk.login.UserTypeSelectActivity

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