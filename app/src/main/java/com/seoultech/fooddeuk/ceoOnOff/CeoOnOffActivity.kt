package com.seoultech.fooddeuk.ceoOnOff

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import androidx.databinding.DataBindingUtil
import com.seoultech.fooddeuk.MainActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCeoOnOffBinding
import com.seoultech.fooddeuk.storeSetting.StoreSettingActivity

class CeoOnOffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCeoOnOffBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ceo_on_off)

        init()
        setOnClickListeners()
    }

    private fun init() {
        binding.layoutOnOffToggle.onOrOff = true // true = on, false = off (처음에는 on로 초기화)
        binding.onOrOff = true
    }

    private fun setOnClickListeners() {
        binding.layoutOnOffToggle.btnOn.setOnClickListener {
            binding.layoutOnOffToggle.onOrOff = true
            binding.onOrOff = true
        }
        binding.layoutOnOffToggle.btnOff.setOnClickListener {
            binding.layoutOnOffToggle.onOrOff = false
            binding.onOrOff = false
        }
        binding.btnOnSettingSave.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}