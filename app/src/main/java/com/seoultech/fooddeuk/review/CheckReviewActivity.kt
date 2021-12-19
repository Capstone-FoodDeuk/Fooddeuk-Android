package com.seoultech.fooddeuk.review

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCheckReviewBinding
import kotlin.math.max

class CheckReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckReviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // back click
        binding.ivBack.setOnClickListener {
            finish()
        }

        var tasteEval : Int
        var amountEval : Int
        var kindEval : Int
        var tasteArray = arrayOf("맛있어요!", "보통이에요", "별로예요")
        var amountArray = arrayOf("만족해요!", "보통이에요", "부족해요")
        var kindArray = arrayOf("친절해요!", "보통이에요", "불친절해요")

        val storeName = "맛있다코야끼"
        val category = "타코야끼"
        val starScore = 4.2

        val tasteGood = 10
        val tasteSoSo = 6
        val tasteBad = 4
        val amountGood = 3
        val amountSoSo = 7
        val amountBad = 10
        val kindGood = 8
        val kindSoSo = 10
        val kindBad = 2

        //상단 카드뷰의 가게명, 카테고리, 평가 인원, 별점 세팅
        binding.tvStoreName.text = storeName

        if (category != null) {
            setCategoryImage(category)
        }

        tasteEval = tasteGood + tasteSoSo + tasteBad
        amountEval = amountGood + amountSoSo + amountBad
        kindEval = kindGood + kindSoSo + kindBad

        binding.tvNumEval.text = "총 " + tasteEval + "명이 평가하였습니다"

        binding.rbStarReview.setReviewScore(starScore.toFloat())
        binding.tvStarScore.text = starScore.toString()

        binding.tvTasteBest.text = "'" + tasteArray[getBestReview(tasteGood, tasteSoSo, tasteBad)] + "'를 가장 많이 받았어요!"
        binding.tvAmountBest.text = "'" + amountArray[getBestReview(amountGood, amountSoSo, amountBad)] + "'를 가장 많이 받았어요!"
        binding.tvKindBest.text = "'" + kindArray[getBestReview(kindGood, kindSoSo, kindBad)] + "'를 가장 많이 받았어요!"

        binding.tvTasteGoodRatio.text = "(" + getPercentage(tasteEval, tasteGood) + "%)"
        binding.tvTasteSosoRatio.text = "(" + getPercentage(tasteEval, tasteSoSo) + "%)"
        binding.tvTasteBadRatio.text = "(" + getPercentage(tasteEval, tasteBad) + "%)"
        binding.tvAmountGoodRatio.text = "(" + getPercentage(amountEval, amountGood) + "%)"
        binding.tvAmountSosoRatio.text = "(" + getPercentage(amountEval, amountSoSo) + "%)"
        binding.tvAmountBadRatio.text = "(" + getPercentage(amountEval, amountBad) + "%)"
        binding.tvKindGoodRatio.text = "(" + getPercentage(kindEval, kindGood) + "%)"
        binding.tvKindSosoRatio.text = "(" + getPercentage(kindEval, kindSoSo) + "%)"
        binding.tvKindBadRatio.text = "(" + getPercentage(kindEval, kindBad) + "%)"
    }

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
        if(category == "타코야끼")
            binding.ivCategory.setImageResource(R.drawable.ic_category_tako)
        else if(category == "군밤")
            binding.ivCategory.setImageResource(R.drawable.ic_category_gunbam)
        else if(category == "군고구마")
            binding.ivCategory.setImageResource(R.drawable.ic_category_goguma)
        else if(category == "과일")
            binding.ivCategory.setImageResource(R.drawable.ic_category_apple)
        else if(category == "붕어빵")
            binding.ivCategory.setImageResource(R.drawable.ic_category_bungeo)
        else if(category == "순대")
            binding.ivCategory.setImageResource(R.drawable.ic_category_sundae)
    }
}