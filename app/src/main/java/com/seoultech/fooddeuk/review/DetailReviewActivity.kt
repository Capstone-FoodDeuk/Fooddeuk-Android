    package com.seoultech.fooddeuk.review

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.view.View
    import android.view.animation.AnimationUtils
    import android.widget.Toast
    import androidx.lifecycle.ViewModelProvider
    import com.seoultech.fooddeuk.R
    import com.seoultech.fooddeuk.databinding.ActivityDetailReviewBinding
    import com.seoultech.fooddeuk.detail.TruckDetailActivity
    import com.seoultech.fooddeuk.model.httpBody.StoreReviewRequest

    class DetailReviewActivity : AppCompatActivity() {

        private lateinit var binding: ActivityDetailReviewBinding
        private lateinit var storeReviewViewModel: StoreReviewViewModel
        lateinit var tasteReview: String
        lateinit var amountReview: String
        lateinit var kindReview: String
        var isCheckedTaste:Boolean = false
        var isCheckedAmount:Boolean = false
        var isCheckedKind:Boolean = false
        var storeId:Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            binding = ActivityDetailReviewBinding.inflate(layoutInflater)
            val view = binding.root
            setContentView(view)

            binding.ivBack.setOnClickListener {
                finish()
            }

            // view model
            storeReviewViewModel = ViewModelProvider(this).get(StoreReviewViewModel::class.java)
            subscribeViewModel()

            //이전 화면에서 받아온 별 갯수와 카테고리 이미지 세팅
            val numStars = intent.getLongExtra("numStars", 0)
            val storeCategory = intent.getStringExtra("category")
            val storeId = intent.getIntExtra("id", 0)
            setCategoryNStars(numStars, storeCategory)

            //리뷰 입력 세팅
            setTasteReview()
            setAmountReview()
            setKindReview()

            binding.btnReviewOk.setOnClickListener {
                // 통신
                val storeReviewInfo = getStoreReviewInfoFromView(numStars)
                callStoreReviewAPI(storeReviewInfo)

                // 트럭별 상세보기로 이동
                val intent = Intent(this, TruckDetailActivity::class.java)
                startActivity(intent)
            }
        }

        private fun subscribeViewModel() {
            storeReviewViewModel.storeReviewOkCode.observe(this, {
                if (it) {
                    Toast.makeText(this, "리뷰 작성이 완료되었습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "가게 설정이 실패했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                }
            })
        }

        private fun getStoreReviewInfoFromView(numStars: Long): StoreReviewRequest {
            return StoreReviewRequest(
                score = numStars,
                taste = tasteReview,
                quantity = amountReview,
                kindness = kindReview
            )
        }

        private fun callStoreReviewAPI(storeReviewInfo: StoreReviewRequest) = storeReviewViewModel.requestStoreReview(storeId)

        private fun setCategoryNStars(numStars: Long, storeCategory: String?) {
            binding.rbStars.setReviewScore(numStars.toFloat())

            if(storeCategory == "Takoyaki")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_tako)
            else if(storeCategory == "Gunbam")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_gunbam)
            else if(storeCategory == "Goguma")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_goguma)
            else if(storeCategory == "Apple")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_apple)
            else if(storeCategory == "Bungeoppang")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_bungeo)
            else if(storeCategory == "Sundae")
                binding.ivDetailCategory.setImageResource(R.drawable.ic_category_sundae)
        }

        // 각각 버튼 눌렀을 때 good/soso/bad 설정, 카테고리별 눌렸는지 여부 설정(총 리뷰/리뷰 작성 완료 버튼 활성화 위해)
        private fun setTasteReview() {
            binding.btnTasteGood.setOnClickListener {
                binding.ivTotalTaste.setImageResource(R.drawable.ic_emoji_good)
                binding.tvTotalTaste.text = "맛있어요!"

                tasteReview = "Good"
                isCheckedTaste = true

                isCheckedAll()
            }

            binding.btnTasteSoso.setOnClickListener {
                binding.ivTotalTaste.setImageResource(R.drawable.ic_emoji_soso)
                binding.tvTotalTaste.text = "보통이에요"

                tasteReview = "SoSo"
                isCheckedTaste = true

                isCheckedAll()
            }

            binding.btnTasteBad.setOnClickListener {
                binding.ivTotalTaste.setImageResource(R.drawable.ic_emoji_bad)
                binding.tvTotalTaste.text = "별로예요"

                tasteReview = "Bad"
                isCheckedTaste = true

                isCheckedAll()
            }
        }

        private fun setAmountReview() {
            binding.btnAmountGood.setOnClickListener {
                binding.ivTotalAmount.setImageResource(R.drawable.ic_emoji_good)
                binding.tvTotalAmount.text = "만족해요!"

                amountReview = "Enough"
                isCheckedAmount = true

                isCheckedAll()
            }

            binding.btnAmountSoso.setOnClickListener {
                binding.ivTotalAmount.setImageResource(R.drawable.ic_emoji_soso)
                binding.tvTotalAmount.text = "보통이에요"

                amountReview = "SoSo"
                isCheckedAmount = true

                isCheckedAll()
            }

            binding.btnAmountBad.setOnClickListener {
                binding.ivTotalAmount.setImageResource(R.drawable.ic_emoji_bad)
                binding.tvTotalAmount.text = "부족해요"

                amountReview = "Bad"
                isCheckedAmount = true

                isCheckedAll()
            }
        }

        private fun setKindReview() {
            binding.btnKindGood.setOnClickListener {
                binding.ivTotalKind.setImageResource(R.drawable.ic_emoji_good)
                binding.tvTotalKind.text = "친절해요!"

                kindReview = "Kind"
                isCheckedKind = true

                isCheckedAll()
            }

            binding.btnKindSoso.setOnClickListener {
                binding.ivTotalKind.setImageResource(R.drawable.ic_emoji_soso)
                binding.tvTotalKind.text = "보통이에요"

                kindReview = "SoSo"
                isCheckedKind = true

                isCheckedAll()
            }

            binding.btnKindBad.setOnClickListener {
                binding.ivTotalKind.setImageResource(R.drawable.ic_emoji_bad)
                binding.tvTotalKind.text = "불친절해요"

                kindReview = "Bad"
                isCheckedKind = true

                isCheckedAll()
            }
        }

        private fun isCheckedAll() {
            if(isCheckedTaste && isCheckedAmount && isCheckedKind) {
                val reviewAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.review_ok_animation)

                binding.totalBox.visibility = View.VISIBLE
                binding.btnReviewOk.visibility = View.VISIBLE
                binding.cvTotalBox.visibility = View.VISIBLE
                binding.totalBox.animation = reviewAnim
                binding.btnReviewOk.animation = reviewAnim
                binding.cvTotalBox.animation = reviewAnim
            }
        }
    }