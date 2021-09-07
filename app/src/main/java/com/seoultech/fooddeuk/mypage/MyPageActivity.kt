package com.seoultech.fooddeuk.mypage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityMyPageBinding
import com.seoultech.fooddeuk.dialog.NickNameSettingDialog

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val list = listOf(FragmentDibsList(), FragmentReviewList())
        val pagerAdatper = FragmentPagerAdapter(list, this)
        binding.viewPager.adapter = pagerAdatper
        val titles = listOf("찜한 가게", "내가 쓴 리뷰")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.get(position)
        }.attach()

        //닉네임 수정 다이얼로그
        binding.ivNickNameSetting.setOnClickListener {
            NickNameSettingDialog().show(supportFragmentManager, "layout_dialog_nick_name")
        }
    }
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)
}