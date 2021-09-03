package com.seoultech.fooddeuk.ceoOnOff

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.DataBindingUtil
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCeoOnOffBinding

class CeoOnOffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCeoOnOffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ceo_on_off)

        init()
        setOnClickOffToggleButtonListener()
    }

    private fun init() {
        binding.layoutOnOffToggle.onOrOff = true // true = on, false = off (처음에는 on로 초기화)
        binding.onOrOff = true
    }

    private fun setOnClickOffToggleButtonListener() {
        binding.layoutOnOffToggle.btnOn.setOnClickListener {
            binding.layoutOnOffToggle.onOrOff = true
            binding.onOrOff = true
        }
        binding.layoutOnOffToggle.btnOff.setOnClickListener {
            binding.layoutOnOffToggle.onOrOff = false
            binding.onOrOff = false
        }
    }
}