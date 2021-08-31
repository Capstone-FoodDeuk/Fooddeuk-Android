package com.seoultech.fooddeuk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.databinding.ActivityOnOffBinding

class OnOffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnOffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnOffBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}