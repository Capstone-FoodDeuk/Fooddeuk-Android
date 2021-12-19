package com.seoultech.fooddeuk.mypage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.databinding.ActivityMyPageBinding
import com.seoultech.fooddeuk.dialog.NickNameSettingDialog

class MyPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyPageBinding
    private lateinit var nicknameViewModel: MyPageViewModel
    var nickname: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = listOf(FragmentDibsList(), FragmentReviewList())
        val pagerAdatper = FragmentPagerAdapter(list, this)
        binding.viewPager.adapter = pagerAdatper
        val titles = listOf("찜한 가게", "내가 쓴 리뷰")
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles.get(position)
        }.attach()

        //back 버튼
        binding.ivMypageBack.setOnClickListener {
            finish()
        }

        nicknameViewModel = ViewModelProvider(this).get(MyPageViewModel::class.java)
        subscribeViewModel()

        callNickNameAPI()

        //닉네임 수정 다이얼로그
        binding.ivNicknameSetting.setOnClickListener {
            NickNameSettingDialog().show(supportFragmentManager, "layout_dialog_nick_name")
        }

        //로그아웃 버튼
        binding.ivLogout.setOnClickListener {
            Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
        }
    }
    private fun subscribeViewModel() {
        nicknameViewModel.myPageOkCode.observe(this, {
            if (it) {
                nickname = nicknameViewModel.myPageData.nickname
                Log.i("mypage : nickname", nickname)
                binding.tvNickname.text = nickname+"님"
            } else {
                Toast.makeText(this, "리뷰 목록을 가져 오는 데 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun callNickNameAPI() = nicknameViewModel.requestMyPageInfo()
}

class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList.get(position)
}