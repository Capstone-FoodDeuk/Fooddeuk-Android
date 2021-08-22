package com.seoultech.fooddeuk

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.databinding.ActivityMapBinding
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setKakaoMap()
    }

    private fun setKakaoMap() {
        val kakaoMapView = MapView(this)
        binding.clMap.addView(kakaoMapView)
    }
}