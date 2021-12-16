package com.seoultech.fooddeuk.ceoOnOff

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityCeoOnOffBinding
import com.seoultech.fooddeuk.model.httpBody.OpenInfoRequest
import com.seoultech.fooddeuk.review.CheckReviewActivity
import com.seoultech.fooddeuk.storeSetting.StoreSettingActivity
import com.seoultech.fooddeuk.util.AndroidPermissionManager
import java.time.LocalDateTime

class CeoOnOffActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCeoOnOffBinding
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var viewModel: CeoOnOffViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ceo_on_off)

        // set things or init
        init()
        setClickListeners()

        // observe viewModel
        viewModel = ViewModelProvider(this).get(CeoOnOffViewModel::class.java)
        subscribeViewModel()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            LOCATION_PERMISSIONS_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 사용자가 권한 허용한 경우
                } else {
                    // TODO : 사용자가 권한 거부한 경우에 해야할 작업 하라고 onRequestPermissionsResult() 호출함
                }
            }
        }
    }

    private fun subscribeViewModel() {
        viewModel.truckOpenOkCode.observe(this, {
            if (it) {
                Toast.makeText(this, "트럭 오픈! 푸드득 손님용 앱 지도에 표시됩니다~", Toast.LENGTH_SHORT).show()
                binding.btnOnSettingSave.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "죄송합니다. 푸드득 앱 문제로 오픈 설정에 실패했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        binding.layoutOnOffToggle.onOrOff = true // true = on, false = off (처음에는 on로 초기화)
        binding.onOrOff = true
    }

    private fun setClickListeners() {
        binding.apply {
            layoutOnOffToggle.btnOn.setOnClickListener {
                binding.layoutOnOffToggle.onOrOff = true
                binding.onOrOff = true
            }
            layoutOnOffToggle.btnOff.setOnClickListener {
                binding.layoutOnOffToggle.onOrOff = false
                binding.onOrOff = false
            }
            btnOnSettingSave.setOnClickListener {
                val openInfo = getOpenInfoFromView()
                callTruckOpenAPI(openInfo)
            }
            ivHamburger.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START, true)
            }
            navigationMypage.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.truck_setting -> {
                        val intent = Intent(this@CeoOnOffActivity, StoreSettingActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.review_manager -> {
                        val intent = Intent(this@CeoOnOffActivity, CheckReviewActivity::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
            layoutOnTruckLocationInput.tvTruckCurrentLocation.setOnClickListener {
                checkLocationPermission() // 위치 권한 허용 여부 확인
                locationProviderClient = LocationServices.getFusedLocationProviderClient(this@CeoOnOffActivity)
                locationProviderClient.lastLocation.addOnSuccessListener { // 마지막 위치 가져오기
                    layoutOnTruckLocationInput.openLocationInput.setText("위도 = ${it.latitude}, 경도 = ${it.longitude}")
                    viewModel.currentLatitude = it.latitude
                    viewModel.currentLongitude = it.longitude
                }
            }
        }
    }

    /**
     * 사용자가 위치 권한을 허용했는지 여부 확인 후,
     * 허용하지 않았을 경우에만 권한 요청 팝업 띄움
     */
    private fun checkLocationPermission() {
        AndroidPermissionManager
            .request(this, this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    private fun getOpenInfoFromView(): OpenInfoRequest = OpenInfoRequest(
        latitude = viewModel.currentLatitude,
        longitude = viewModel.currentLongitude,
        closeTime = LocalDateTime.now().withHour(binding.layoutOnTruckOffTime.tpTruckOffTime.hour).withMinute(binding.layoutOnTruckOffTime.tpTruckOffTime.minute).toString()
    )

    private fun callTruckOpenAPI(openInfo: OpenInfoRequest) = viewModel.requestTruckOpen(openInfo)

    companion object {
        const val LOCATION_PERMISSIONS_REQUEST_CODE = 100
    }
}