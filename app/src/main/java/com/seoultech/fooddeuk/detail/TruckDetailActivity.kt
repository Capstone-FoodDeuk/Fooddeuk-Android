package com.seoultech.fooddeuk.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.seoultech.fooddeuk.databinding.ActivityTruckDetailBinding
import com.seoultech.fooddeuk.dialog.NoTruckDialog
import com.seoultech.fooddeuk.model.enums.Category
import com.seoultech.fooddeuk.review.StarReviewActivity

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
        callTruckDetailInfoAPI(1) // TODO: (chohee) storeId 임의, 변경 필요
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
                callTruckLikeAPI(1) // TODO : 임시 id 값 넘겨줌, 변경 필요
            }
            ivBackArrow.setOnClickListener { finish() }
            btnWriteReview.setOnClickListener {
                val intent = Intent(this@TruckDetailActivity, StarReviewActivity::class.java)
                intent.putExtra("storeId", 1) // TODO : 임시 id 값 넘겨줌, 변경 필요
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
                binding.layoutTruckDetailTitle.tvTruckName.text = truckDetailData.name
                binding.layoutTruckDetailTitle.tvTruckCategory.text = getCategoryNameByKorean(truckDetailData.category)
                binding.layoutTruckDetailTitle.tvReviewScore.text = getReviewScore(truckDetailData.rating.totalSum, truckDetailData.rating.userCnt).toString()
                binding.layoutTruckDetailBusinessInfo.tvBusinessTime.text = "${truckDetailData.closeTime.substring(11 until 16)} 마감" // 00:00 부분 추출
                phoneNumber = truckDetailViewModel.truckDetailData.phoneNumber
                menuListAdapter.setDataSet(truckDetailViewModel.truckDetailData.menuList)
                binding.layoutTruckDetailReview.tvScore.text = getReviewScore(truckDetailData.rating.totalSum, truckDetailData.rating.userCnt).toString()
                binding.ivLike.isChecked = truckDetailData.liked
                truckDetailData.paymentMethods.forEach {
                    binding.layoutTruckDetailPayType.payTypeBankTransfer.visibility = if (it == binding.layoutTruckDetailPayType.payTypeBankTransfer.tag) View.VISIBLE else View.GONE
                    binding.layoutTruckDetailPayType.payTypeCash.visibility = if (it == binding.layoutTruckDetailPayType.payTypeCash.tag) View.VISIBLE else View.GONE
                    binding.layoutTruckDetailPayType.payTypeCard.visibility = if (it == binding.layoutTruckDetailPayType.payTypeCard.tag) View.VISIBLE else View.GONE
                }
                binding.layoutTruckDetailReview.rbStars.setReviewScore(3.0f) // TODO : (chohee) 임시 점수임
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

    private fun getCategoryNameByKorean(category: String): String {
        return when (category) {
            Category.TAKOYAKI.serverName -> Category.TAKOYAKI.koreanName
            Category.FISHBREAD.serverName -> Category.FISHBREAD.koreanName
            Category.FRUIT.serverName -> Category.FRUIT.koreanName
            Category.CHESTNUTS.serverName -> Category.CHESTNUTS.koreanName
            else -> Category.UNDEFINED.koreanName
        }
    }

    private fun getReviewScore(userCount: Int, totalSum: Int) = if (userCount != 0) totalSum.toDouble() / userCount else 0

    private fun callTruckDetailInfoAPI(storeId: Int) = truckDetailViewModel.requestTruckDetailInfo(storeId)

    private fun callTruckLikeAPI(storeId: Int) = truckDetailViewModel.requestTruckLike(storeId)
}