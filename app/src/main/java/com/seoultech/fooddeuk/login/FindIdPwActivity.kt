package com.seoultech.fooddeuk.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityFindIdPwBinding

class FindIdPwActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFindIdPwBinding
    private val findIdFragment: FindIdFragment = FindIdFragment()
    private val findPwFragment: FindPwFragment = FindPwFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFindIdPwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTabFragment()
        setTabSelectedListener()
        setOnClickListeners()
    }

    private fun initTabFragment() {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fcv_tab_fragment, findPwFragment)
            add(R.id.fcv_tab_fragment, findIdFragment) // 위로 쌓이는 구조라서 FindPwFragment 부터 add함
        }.commit()
    }

    private fun setTabSelectedListener() {
        binding.tlFindIdPassword.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> supportFragmentManager.beginTransaction().replace(R.id.fcv_tab_fragment, findIdFragment).commit()
                    1 -> supportFragmentManager.beginTransaction().replace(R.id.fcv_tab_fragment, findPwFragment).commit()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) { }

            override fun onTabReselected(tab: TabLayout.Tab?) { }
        })
    }

    private fun setOnClickListeners() {
        binding.ivBackArrow.setOnClickListener {
            finish()
        }
    }
}