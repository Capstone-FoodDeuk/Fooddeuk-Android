package com.seoultech.fooddeuk.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.databinding.ActivityFindIdPwBinding

class FindIdPwActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindIdPwBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindIdPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setTabSelectedListener()
    }

    private fun setTabSelectedListener() {
        binding.tlFindIdPassword.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }
        })
    }

}