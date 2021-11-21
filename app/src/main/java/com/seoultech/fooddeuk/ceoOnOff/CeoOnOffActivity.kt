package com.seoultech.fooddeuk.ceoOnOff

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
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
        binding.apply {
            layoutOnOffToggle.btnOn.setOnClickListener {
                binding.layoutOnOffToggle.onOrOff = true
                binding.onOrOff = true
            }
            layoutOnOffToggle.btnOff.setOnClickListener {
                binding.layoutOnOffToggle.onOrOff = false
                binding.onOrOff = false
            }
            btnOnSettingSave.setOnClickListener {
                val intent = Intent(this@CeoOnOffActivity, MainActivity::class.java)
                startActivity(intent)
            }
            ivHamburger.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START, true)
            }
            navigationMypage.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.truck_setting -> {
                        val intent = Intent(this@CeoOnOffActivity, StoreSettingActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
        }
    }
}