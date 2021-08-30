package com.seoultech.fooddeuk.review

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import com.seoultech.fooddeuk.MainActivity
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityDetailReviewBinding
import com.seoultech.fooddeuk.intro.IntroCustomerActivity

class DetailReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailReviewBinding
    var tasteReview:String? = null
    var amountReview:String? = null
    var kindReview:String? = null
    var isCheckedTaste:Boolean = false
    var isCheckedAmount:Boolean = false
    var isCheckedKind:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityDetailReviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val intent = intent
        val numStars = intent.getFloatExtra("numStars", 0.0F)
        val storeCategory = intent.getStringExtra("category")

        //TODO:뒤로가기 버튼

        //이전 화면에서 받아온 별 갯수와 카테고리 이미지 세팅
        binding.rbStars.rating = numStars

        if(storeCategory == "타코야끼")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_tako)
        else if(storeCategory == "군밤")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_gunbam)
        else if(storeCategory == "군고구마")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_goguma)
        else if(storeCategory == "과일")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_apple)
        else if(storeCategory == "붕어빵")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_bungeo)
        else if(storeCategory == "순대")
            binding.ivDetailCategory.setImageResource(R.drawable.ic_food_sundae)

        //리뷰 입력 세팅
        setTasteReview()
        setAmountReview()
        setKindReview()

        //TODO:작성 완료 버튼(현재 Main으로, 추후 트럭별 상세보기로 이동)
        binding.btnReviewOk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    // 각각 버튼 눌렀을 때 good/soso/bad 설정, 카테고리별 눌렸는지 여부 설정(총 리뷰/리뷰 작성 완료 버튼 활성화 위해)
    private fun setTasteReview() {
        binding.btnTasteGood.setOnClickListener {
            binding.btnTasteGood.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnTasteGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnTasteSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnTasteBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalTaste.setImageResource(R.drawable.ic_review_bird_good)
            binding.tvTotalTaste.text = "맛있어요!"

            tasteReview = "good"
            isCheckedTaste = true

            isCheckedAll()
        }

        binding.btnTasteSoso.setOnClickListener {
            binding.btnTasteSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnTasteSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnTasteGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnTasteBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalTaste.setImageResource(R.drawable.ic_review_bird_soso)
            binding.tvTotalTaste.text = "보통이에요"

            tasteReview = "soso"
            isCheckedTaste = true

            isCheckedAll()
        }

        binding.btnTasteBad.setOnClickListener {
            binding.btnTasteBad.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnTasteBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnTasteGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnTasteSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnTasteSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalTaste.setImageResource(R.drawable.ic_review_bird_bad)
            binding.tvTotalTaste.text = "별로예요"

            tasteReview = "bad"
            isCheckedTaste = true

            isCheckedAll()
        }
    }

    private fun setAmountReview() {
        binding.btnAmountGood.setOnClickListener {
            binding.btnAmountGood.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnAmountGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnAmountSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnAmountBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalAmount.setImageResource(R.drawable.ic_review_bird_good)
            binding.tvTotalAmount.text = "만족해요!"

            amountReview = "good"
            isCheckedAmount = true

            isCheckedAll()
        }

        binding.btnAmountSoso.setOnClickListener {
            binding.btnAmountSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnAmountSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnAmountGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnAmountBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalAmount.setImageResource(R.drawable.ic_review_bird_soso)
            binding.tvTotalAmount.text = "보통이에요"

            amountReview = "soso"
            isCheckedAmount = true

            isCheckedAll()
        }

        binding.btnAmountBad.setOnClickListener {
            binding.btnAmountBad.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnAmountBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnAmountGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnAmountSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnAmountSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalAmount.setImageResource(R.drawable.ic_review_bird_bad)
            binding.tvTotalAmount.text = "부족해요"

            amountReview = "bad"
            isCheckedAmount = true

            isCheckedAll()
        }
    }

    private fun setKindReview() {
        binding.btnKindGood.setOnClickListener {
            binding.btnKindGood.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnKindGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnKindSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnKindBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalKind.setImageResource(R.drawable.ic_review_bird_good)
            binding.tvTotalKind.text = "친절해요!"

            kindReview = "good"
            isCheckedKind = true

            isCheckedAll()
        }

        binding.btnKindSoso.setOnClickListener {
            binding.btnKindSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnKindSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnKindGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnKindBad.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalKind.setImageResource(R.drawable.ic_review_bird_soso)
            binding.tvTotalKind.text = "보통이에요"

            kindReview = "soso"
            isCheckedKind = true

            isCheckedAll()
        }

        binding.btnKindBad.setOnClickListener {
            binding.btnKindBad.background = ContextCompat.getDrawable(this, R.drawable.review_button_click)
            binding.btnKindBad.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_main_color))

            binding.btnKindGood.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindGood.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.btnKindSoso.background = ContextCompat.getDrawable(this, R.drawable.review_button)
            binding.btnKindSoso.setTextColor(ContextCompat.getColor(this, R.color.food_deuk_text_c))

            binding.ivTotalKind.setImageResource(R.drawable.ic_review_bird_bad)
            binding.tvTotalKind.text = "불친절해요"

            kindReview = "bad"
            isCheckedKind = true

            isCheckedAll()
        }
    }

    private fun isCheckedAll() {
        if(isCheckedTaste && isCheckedAmount && isCheckedKind) {
            val reviewAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.review_ok_animation)

            binding.totalBox.visibility = View.VISIBLE
            binding.btnReviewOk.visibility = View.VISIBLE
            binding.totalBox.animation = reviewAnim
            binding.btnReviewOk.animation = reviewAnim
        }
    }
}