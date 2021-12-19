package com.seoultech.fooddeuk.review

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCheckReviewBinding
import java.lang.Math.round
import kotlin.math.max

class CheckReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckReviewBinding
    private lateinit var checkReviewViewModel: CheckReviewViewModel

    val tasteArray = arrayOf("맛있어요!", "보통이에요", "별로예요")
    val amountArray = arrayOf("만족해요!", "보통이에요", "부족해요")
    val kindArray = arrayOf("친절해요!", "보통이에요", "불친절해요")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back click
        binding.ivBack.setOnClickListener {
            finish()
        }

        checkReviewViewModel = ViewModelProvider(this).get(CheckReviewViewModel::class.java)
        subscribeViewModel()

        callCheckReviewAPI()
    }

    private fun subscribeViewModel() {
        checkReviewViewModel.checkReviewOkCode.observe(this, {
            if (it) {
                val checkReviewData = checkReviewViewModel.checkReviewData

                //상단 카드뷰 세팅
                binding.tvStoreName.text = checkReviewData.name
                setCategoryImage(checkReviewData.category)
                binding.tvNumEval.text = "총 " + checkReviewData.userCnt + "명이 평가하였습니다"
                binding.rbStarReview.setReviewScore(round((checkReviewData.totalSum.toFloat()/checkReviewData.userCnt)*10)/10.0.toFloat())
                binding.tvStarScore.text = (round((checkReviewData.totalSum.toFloat()/checkReviewData.userCnt)*10)/10.0).toString()

                //가장 많이 받았어요 텍스트 세팅
                binding.tvTasteBest.text = "'" + tasteArray[getBestReview(checkReviewData.taste.Good, checkReviewData.taste.SoSo, checkReviewData.taste.Bad)] + "'를 가장 많이 받았어요!"
                binding.tvAmountBest.text = "'" + amountArray[getBestReview(checkReviewData.quantity.Enough, checkReviewData.quantity.SoSo, checkReviewData.quantity.Bad)] + "'를 가장 많이 받았어요!"
                binding.tvKindBest.text = "'" + kindArray[getBestReview(checkReviewData.kind.Kind, checkReviewData.kind.SoSo, checkReviewData.kind.Bad)] + "'를 가장 많이 받았어요!"

                //퍼센티지 세팅
                if(checkReviewData.userCnt!=0) {
                    binding.tvTasteGoodRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.taste.Good) + "%)"
                    binding.tvTasteSosoRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.taste.SoSo) + "%)"
                    binding.tvTasteBadRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.taste.Bad) + "%)"
                    binding.tvAmountGoodRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.quantity.Enough) + "%)"
                    binding.tvAmountSosoRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.quantity.SoSo) + "%)"
                    binding.tvAmountBadRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.quantity.Bad) + "%)"
                    binding.tvKindGoodRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.kind.Kind) + "%)"
                    binding.tvKindSosoRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.kind.SoSo) + "%)"
                    binding.tvKindBadRatio.text = "(" + getPercentage(checkReviewData.userCnt, checkReviewData.kind.Bad) + "%)"
                }

                Toast.makeText(this, "리뷰 조회가 완료되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "리뷰 조회가 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callCheckReviewAPI() = checkReviewViewModel.requestCheckReviewInfo()

    private fun getBestReview(num1:Int, num2:Int, num3:Int): Int {
        var maxNum : Int
        maxNum = max(num1, num2)
        maxNum = max(maxNum, num3)

        when (maxNum) {
            num1 -> return 0
            num2 -> return 1
            num3 -> return 2
        }
        return 3
    }

    private fun getPercentage(totalNum:Int, targetNum:Int): Int {
        return targetNum*100/totalNum
    }

    private fun setCategoryImage(category:String) {
        if(category == "Takoyaki")
            binding.ivCategory.setImageResource(R.drawable.ic_category_tako)
        else if(category == "Chestnuts")
            binding.ivCategory.setImageResource(R.drawable.ic_category_gunbam)
        else if(category == "GOGUMA")
            binding.ivCategory.setImageResource(R.drawable.ic_category_goguma)
        else if(category == "Fruit")
            binding.ivCategory.setImageResource(R.drawable.ic_category_apple)
        else if(category == "FishBread")
            binding.ivCategory.setImageResource(R.drawable.ic_category_bungeo)
        else if(category == "Snack")
            binding.ivCategory.setImageResource(R.drawable.ic_category_sundae)
    }
}