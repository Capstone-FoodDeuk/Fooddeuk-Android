package com.seoultech.fooddeuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.seoultech.fooddeuk.databinding.ActivityIntroCeoBinding

class IntroCEOActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroCeoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroCeoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val tab = binding.tabIndication
        val viewPager = binding.screenViewPager
        val skip = binding.btnSkip
        val btnStarted = binding.btnStarted

        //fill list screen
        var mList : MutableList<ScreenItem> = mutableListOf()
        mList.add(ScreenItem("설정해요!", "영업 시작시 설정하는 정보는 당일만 적용되며,\n설정값은 영업 마감시 초기화됩니다.\n시작 전 오픈 시간과 위치를 설정해주세요!", R.drawable.gceo1))
        mList.add(ScreenItem("수정해요!", "푸드트럭 이동시 위치 정보를 변동된 장소로\n수정해주세요. 만약 기재하신 위치와 현 위치가\n다르다면 푸쉬 알람이 가니 확인 부탁드려요.", R.drawable.gceo2))
        mList.add(ScreenItem("확인해요!", "우측 상단에 있는 버튼을 눌러보세요.\n지도보기, 가게 정보 및 메뉴 설정/변경,\n손님들이 남긴 리뷰 확인 등이 가능합니다.", R.drawable.gceo3))
        mList.add(ScreenItem("푸드득!", "사장님의 푸드트럭을 주민들이 기다려요!\n어서 빨리 판매하러 가볼까요?", R.drawable.gceo4))

        //setup viewpager
        val introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        binding.screenViewPager.adapter = introViewPagerAdapter

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        //start
        btnStarted.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun loadLastScreen() {
        val btnStarted = binding.btnStarted
        val tab = binding.tabIndication
        val skip = binding.btnSkip
        val btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.btn_animation)
        btnStarted.visibility = View.VISIBLE
        tab.visibility = View.INVISIBLE
        skip.visibility = View.INVISIBLE
        btnStarted.animation = btnAnim
    }

    private fun loadTab() {
        val btnStarted = binding.btnStarted
        val tab = binding.tabIndication
        val skip = binding.btnSkip
        tab.visibility = View.VISIBLE
        skip.visibility = View.VISIBLE
        btnStarted.visibility = View.INVISIBLE
    }
}