package com.seoultech.fooddeuk.storeSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityStoreSettingBinding

class StoreSettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStoreSettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityStoreSettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnCategoryTako.setOnClickListener {
            setTakoClick()
        }
        binding.btnCategoryGunbam.setOnClickListener {
            setGunbamClick()
        }
        binding.btnCategoryGoguma.setOnClickListener {
            setGogumaClick()
        }
        binding.btnCategoryApple.setOnClickListener {
            setAppleClick()
        }
        binding.btnCategoryBungeo.setOnClickListener {
            setBungeoClick()
        }
        binding.btnCategorySundae.setOnClickListener {
            setSundaeClick()
        }

        binding.btnCertification.setOnClickListener {
            Toast.makeText(this, "인증 완료", Toast.LENGTH_SHORT).show()
        }

        binding.btnSettingOk.setOnClickListener {
            Toast.makeText(this, "설정 완료", Toast.LENGTH_SHORT).show()
        }
    }
    private fun setTakoClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setGunbamClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setGogumaClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setAppleClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setBungeoClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
    }

    private fun setSundaeClick() {
        binding.btnCategoryTako.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGunbam.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryGoguma.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryApple.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategoryBungeo.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_box_light_gray))
        binding.btnCategorySundae.setBackgroundColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))
    }
}