package com.seoultech.fooddeuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.seoultech.fooddeuk.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        selectUserType()
    }

    private fun selectUserType() {
        binding.tvUserTypeCeo.setOnClickListener {
            val intent = Intent(this, CEOLoginActivity::class.java)
            startActivity(intent)
        }
    }
}