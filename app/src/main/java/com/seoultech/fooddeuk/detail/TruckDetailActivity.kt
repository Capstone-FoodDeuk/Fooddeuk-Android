package com.seoultech.fooddeuk.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityTruckDetailBinding
import com.seoultech.fooddeuk.databinding.LayoutTruckDetailTitleBinding
import com.seoultech.fooddeuk.dialog.NoTruckDialog
import com.seoultech.fooddeuk.model.enums.Category

class TruckDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTruckDetailBinding
    private lateinit var truckDetailViewModel: TruckDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruckDetailBinding.inflate(layoutInflater)

        // set things
        setContentView(binding.root)
        setClickListeners()

        // observe viewModel
        truckDetailViewModel = ViewModelProvider(this).get(TruckDetailViewModel::class.java)
        subscribeViewModel()

        callTruckDetailInfoAPI(1) // TODO: (chohee) storeId 임의, 변경 필요
    }

    private fun setClickListeners() {
        binding.layoutTruckDetailTitle.tvReport.setOnClickListener {
            NoTruckDialog().show(supportFragmentManager, "layout_dialog_no_truck") // 다이얼로그 쇼
        }
    }

    private fun subscribeViewModel() {
        truckDetailViewModel.truckDetailInfoOkCode.observe(this, {
            if (it) {
                // TODO : (chohee) 레이아웃 작업 추가 필요
                binding.layoutTruckDetailTitle.apply {
                    tvTruckName.text = truckDetailViewModel.truckDetailData.name
                    tvTruckCategory.text = getCategoryNameByKorean(truckDetailViewModel.truckDetailData.category)
                }
            } else {
                Toast.makeText(this, "죄송합니다. 푸드득 앱에 문제가 생겼어요! 얼른 고칠게요", Toast.LENGTH_SHORT).show()
                finish()
            }
        })
    }

    private fun getCategoryNameByKorean(category: String): String {
        return when (category) {
            Category.TAKOYAKI.serverName -> Category.TAKOYAKI.koreanName
            Category.FISHBREAD.serverName -> Category.FISHBREAD.koreanName
            Category.FRUIT.serverName -> Category.FRUIT.koreanName
            Category.CHESTNUTS.serverName -> Category.CHESTNUTS.koreanName
            else -> Category.UNDEFINED.koreanName
        }
    }

    private fun callTruckDetailInfoAPI(storeId: Int) = truckDetailViewModel.requestTruckDetailInfo(storeId)
}