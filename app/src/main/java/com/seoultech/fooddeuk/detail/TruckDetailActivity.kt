package com.seoultech.fooddeuk.detail

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityTruckDetailBinding
import com.seoultech.fooddeuk.dialog.NoTruckDialog
import com.seoultech.fooddeuk.model.enums.Category
import com.seoultech.fooddeuk.model.enums.PayMethod
import com.seoultech.fooddeuk.model.httpBody.TasteInfo
import com.seoultech.fooddeuk.review.StarReviewActivity
import kotlin.math.round

class TruckDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTruckDetailBinding
    private lateinit var truckDetailViewModel: TruckDetailViewModel
    private lateinit var menuListAdapter: MenuListAdapter
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTruckDetailBinding.inflate(layoutInflater)

        // set things, init things
        setContentView(binding.root)
        setClickListeners()
        initRecyclerView()

        // observe viewModel
        truckDetailViewModel = ViewModelProvider(this).get(TruckDetailViewModel::class.java)
        subscribeViewModel()

        // call api function to get data from server
        callTruckDetailInfoAPI(getStoreId())
    }

    private fun initRecyclerView() {
        menuListAdapter = MenuListAdapter()
        binding.layoutTruckDetailMenu.rvMenuList.apply {
            layoutManager = LinearLayoutManager(this@TruckDetailActivity, LinearLayoutManager.VERTICAL, false)
            adapter = menuListAdapter
        }
    }

    private fun setClickListeners() {
        binding.apply {
            layoutTruckDetailTitle.tvReport.setOnClickListener {
                NoTruckDialog().show(supportFragmentManager, "layout_dialog_no_truck")
            }
            layoutTruckDetailTitle.tvCall.setOnClickListener {
                if (phoneNumber.isEmpty()) {
                    Toast.makeText(this@TruckDetailActivity, "이 트럭은 전화번호가 등록되어 있지 않습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNumber"))
                    startActivity(intent)
                }
            }
            ivLike.setOnClickListener {
                ivLike.toggle()
                callTruckLikeAPI(getStoreId())
            }
            ivBackArrow.setOnClickListener { finish() }
            btnWriteReview.setOnClickListener {
                val intent = Intent(this@TruckDetailActivity, StarReviewActivity::class.java)
                intent.putExtra("storeId", getStoreId())
                intent.putExtra("name", binding.layoutTruckDetailTitle.tvTruckName.text)
                intent.putExtra("category", binding.layoutTruckDetailTitle.tvTruckCategory.text)
                startActivity(intent)
            }
        }
    }

    private fun subscribeViewModel() {
        truckDetailViewModel.truckDetailInfoOkCode.observe(this, {
            if (it) {
                val truckDetailData = truckDetailViewModel.truckDetailData
                binding.ivBackgroundMap.setImageResource(getCategoryBackground(truckDetailData.category))
                binding.layoutTruckDetailTitle.ivTruckCategoryLogo.setImageResource(getCategoryLogo(truckDetailData.category))
                binding.layoutTruckDetailTitle.tvTruckName.text = truckDetailData.name
                binding.layoutTruckDetailTitle.tvTruckCategory.text = getCategoryNameByKorean(truckDetailData.category)
                binding.layoutTruckDetailTitle.tvReviewScore.text = getReviewScore(truckDetailData.rating.totalSum, truckDetailData.rating.userCnt).toString()
                binding.layoutTruckDetailBusinessInfo.tvBusinessTime.text = if (truckDetailData.closeTime.isNotEmpty()) {
                    "${truckDetailData.closeTime.substring(11 until 16)} 마감"
                } else {
                    "마감 정보 없음"
                }
                binding.layoutTruckDetailBusinessInfo.tvBusinessLocation.text = getAddress(truckDetailData.location.latitude, truckDetailData.location.longitude)
                phoneNumber = truckDetailViewModel.truckDetailData.phoneNumber
                menuListAdapter.setDataSet(truckDetailViewModel.truckDetailData.menuList)
                binding.layoutTruckDetailReview.tvScore.text = getReviewScore(truckDetailData.rating.totalSum, truckDetailData.rating.userCnt).toString()
                binding.ivLike.isChecked = truckDetailData.liked
                Log.i("payment", truckDetailData.paymentMethods.toString())
                truckDetailData.paymentMethods.forEach {
                    if (it == PayMethod.BANK_TRANSFER.name)
                        binding.layoutTruckDetailPayType.payTypeBankTransfer.visibility = View.VISIBLE
                    else if (it == PayMethod.CASH.name)
                        binding.layoutTruckDetailPayType.payTypeCash.visibility = View.VISIBLE
                    else if (it == PayMethod.CARD.name)
                        binding.layoutTruckDetailPayType.payTypeCard.visibility = View.VISIBLE
                }
                binding.layoutTruckDetailReview.rbStars.setReviewScore(getReviewScore(truckDetailData.rating.totalSum, truckDetailData.rating.userCnt))
            } else {
                Toast.makeText(this, "죄송합니다. 푸드득 앱에 문제가 생겼어요! 얼른 고칠게요", Toast.LENGTH_SHORT).show()
                finish()
            }
        })

        truckDetailViewModel.truckLikeOkCode.observe(this, {
            if (it) {
                if (binding.ivLike.isChecked) {
                    Toast.makeText(this, "찜 목록에 추가했습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "찜 목록에서 제거했습니다.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "죄송합니다. 푸드득 앱 문제로 찜 등록에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        var geocoder = Geocoder(this)
        var list: List<Address> = geocoder.getFromLocation(latitude, longitude, 2)

        if(list!=null) {
            if (list.size == 0) {
                Log.e("Reverse GeoCoding", "주소값이 없어요")
                return ""
            }
            else {
                var address = list.get(0).getAddressLine(0).toString()
                for (i in 1..3) {
                    address = address.substring(address.indexOf(" ")+1)
                }
                return address
            }
        }
        return ""
    }

    private fun getCategoryNameByKorean(category: String): String {
        return when (category) {
            Category.TAKOYAKI.serverName -> Category.TAKOYAKI.koreanName
            Category.FISHBREAD.serverName -> Category.FISHBREAD.koreanName
            Category.FRUIT.serverName -> Category.FRUIT.koreanName
            Category.CHESTNUTS.serverName -> Category.CHESTNUTS.koreanName
            Category.SNACK.serverName -> Category.SNACK.koreanName
            Category.GOGUMA.serverName -> Category.GOGUMA.koreanName
            else -> Category.UNDEFINED.koreanName
        }
    }

    private fun getCategoryLogo(category: String): Int {
        return when (category) {
            Category.TAKOYAKI.serverName -> R.drawable.ic_category_tako_small
            Category.FISHBREAD.serverName -> R.drawable.ic_category_bungeo_small
            Category.FRUIT.serverName -> R.drawable.ic_category_apple_small
            Category.CHESTNUTS.serverName -> R.drawable.ic_category_gunbam_small
            Category.SNACK.serverName -> R.drawable.ic_category_sundae_small
            Category.GOGUMA.serverName -> R.drawable.ic_category_goguma_small
            else -> R.drawable.ic_category_tako_small
        }
    }

    private fun getCategoryBackground(category: String): Int {
        return when (category) {
            Category.TAKOYAKI.serverName -> R.drawable.ic_review_bottom_tako
            Category.FISHBREAD.serverName -> R.drawable.ic_review_bottom_bungeoppang
            Category.FRUIT.serverName -> R.drawable.ic_review_bottom_apple
            Category.CHESTNUTS.serverName -> R.drawable.ic_review_bottom_gunbam
            Category.SNACK.serverName -> R.drawable.ic_review_bottom_sundae
            Category.GOGUMA.serverName -> R.drawable.ic_review_bottom_goguma
            else -> R.drawable.ic_review_bottom_tako
        }
    }

    private fun getReviewScore(totalSum: Int, userCount: Int):Float = if (userCount != 0) {
        val rawScore = (totalSum.toFloat())/userCount
        round(rawScore * 100) / 100 // 소수점 3번째 자리에서 반올림 한다.
    } else 0.0F

    private fun getStoreId() = intent.getIntExtra("store_id", -1)

    private fun callTruckDetailInfoAPI(storeId: Int) = truckDetailViewModel.requestTruckDetailInfo(storeId)

    private fun callTruckLikeAPI(storeId: Int) = truckDetailViewModel.requestTruckLike(storeId)
}