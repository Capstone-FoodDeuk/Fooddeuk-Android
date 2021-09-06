package com.seoultech.fooddeuk.intro

import android.content.Intent
import com.seoultech.fooddeuk.databinding.ActivityIntroCustomerBinding

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.MainActivity
import com.seoultech.fooddeuk.R


class IntroCustomerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroCustomerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroCustomerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tab = binding.tlIndication
        val viewPager = binding.vpScreen
        val skip = binding.btnSkip
        val btnStarted = binding.btnStart

        //fill list screen
        var mList : MutableList<ScreenItem> = mutableListOf()
        mList.add(
            ScreenItem(
                "확인해요!",
                "기재된 푸드트럭 정보(오픈시간, 날짜)는\n당일에만 해당됩니다. 방문 전, 그 날의\n정보를 한 번 더 확인하고 찾아가주세요.",
                R.drawable.ic_guide_customer_1
            )
        )
        mList.add(
            ScreenItem(
                "연락해요!",
                "푸드트럭이 기재된 장소에 없다면\n‘전화’ 혹은 ‘트럭 없음' 버튼을 통해\n사장님께 연락해보세요.",
                R.drawable.ic_guide_customer_2
            )
        )
        mList.add(
            ScreenItem(
                "찜해요!",
                "하트를 눌러 좋아하는 푸드트럭을 찜해봐요.\n사장님께서 영업을 시작하시면 푸쉬알람을\n보내드릴게요. (알람 수신은 설정 가능합니다)",
                R.drawable.ic_guide_customer_3
            )
        )
        mList.add(
            ScreenItem(
                "푸드득!",
                "자, 이제 우리 동네의 숨은 맛집,\n푸드트럭을 찾으러 가볼까요?",
                R.drawable.ic_guide_customer_4
            )
        )

        //setup viewpager
        val introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        binding.vpScreen.adapter = introViewPagerAdapter

        //setup tablayout with viewpager
        TabLayoutMediator(tab, viewPager) { tab, position ->
        }.attach()

        //setup last view
        tab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if(tab.position == mList.size-1){
                    // 마지막 화면 세팅
                    loadLastScreen()
                }
                else{
                    loadTab()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        //intro skip
        skip.setOnClickListener {
            finish() // 액티비티 백스택 계산해보면 로그인 화면으로 돌아감
        }

        //start
        btnStarted.setOnClickListener {
            finish() // 액티비티 백스택 계산해보면 로그인 화면으로 돌아감
        }
    }

    private fun loadLastScreen() {
        val btnStarted = binding.btnStart
        val tab = binding.tlIndication
        val skip = binding.btnSkip
        val btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.btn_animation)
        btnStarted.visibility = View.VISIBLE
        tab.visibility = View.INVISIBLE
        skip.visibility = View.INVISIBLE
        btnStarted.animation = btnAnim
    }

    private fun loadTab() {
        val btnStarted = binding.btnStart
        val tab = binding.tlIndication
        val skip = binding.btnSkip
        tab.visibility = View.VISIBLE
        skip.visibility = View.VISIBLE
        btnStarted.visibility = View.INVISIBLE
    }

}