package com.seoultech.fooddeuk.map

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityMapBinding
import com.seoultech.fooddeuk.detail.TruckDetailActivity
import com.seoultech.fooddeuk.model.enums.Category
import com.seoultech.fooddeuk.model.httpBody.TruckInfo
import com.seoultech.fooddeuk.mypage.MyPageActivity
import com.seoultech.fooddeuk.util.AndroidPermissionManager
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity() {

    companion object {
        const val LOCATION_PERMISSIONS_REQUEST_CODE = 100
    }

    private lateinit var binding: ActivityMapBinding
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var viewModel: MapViewModel
    private lateinit var kakaoMap: MapView
    private val markerClickListener = MarkerEventListener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)

        // set things
        setContentView(binding.root)
        setClickListeners()

        // observe viewModel
        viewModel = ViewModelProvider(this).get(MapViewModel::class.java)
        subscribeViewModel()
    }

    override fun onResume() {
        super.onResume()
        checkLocationPermission()

        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationProviderClient.lastLocation.addOnSuccessListener {
            initKakaoMap(it.latitude, it.longitude) // latitude = 위도, longitude = 경도
        }
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
        viewModel.homeDataOkCode.observe(this, {
            if (it) {
                viewModel.truckInfoList?.let {
                    list -> setMarkers(list)
                }
            } else {
                Toast.makeText(this, "죄송합니다. 푸드트럭 위치 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initKakaoMap(latitude: Double, longitude: Double) {
        kakaoMap = MapView(this)
        binding.clMap.addView(kakaoMap)

        // 지도 중심점 현재 위치로 세팅(줌 레벨 낮을 수록 Zoom In)
        kakaoMap.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude, longitude), 1,true)
        kakaoMap.setPOIItemEventListener(markerClickListener)

        callHomeDataAPI(latitude, longitude, null) // 카테고리가 null이면 all 카테고리이다.
    }

    private fun setMarkers(truckInfoList: List<TruckInfo>) {
        truckInfoList.forEach {
            addMarker(
                it.latitude,
                it.longitude,
                it.name,
                it.id,
                getCategoryMapMarkerLogo(it.category)
            )
        }
    }

    private fun addMarker(latitude: Double, longitude: Double, itemName: String, tag: Int, image: Int) {
        val marker = MapPOIItem()
        marker.itemName = itemName
        marker.tag = tag
        marker.mapPoint = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.markerType = MapPOIItem.MarkerType.CustomImage
        marker.customImageResourceId = image
        marker.isCustomImageAutoscale = false // 커스텀 이미지 크기 자동 조절
        marker.setCustomImageAnchor(0.5f, 1.0f) // 커스텀 이미지에서 앵커 포인트 지정
        kakaoMap.addPOIItem(marker)
    }

    private fun checkLocationPermission() {
        AndroidPermissionManager
            .request(this, this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    private fun setGoogleServiceAPIAvailability() {
        val status = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)
        if (status != ConnectionResult.SUCCESS) {
            TODO("GoogleService 앱 설치하는 페이지로 넘기거나 Dialog 띄우기")
        }
    }

    private fun setClickListeners() {
        binding.fabCategoryFilter.setOnClickListener {
            val categoryFilterBottomSheet = CategoryFilterBottomSheet()
            categoryFilterBottomSheet.show(supportFragmentManager, CategoryFilterBottomSheet.TAG)
        }
        binding.fabMypage.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getCategoryMapMarkerLogo(category: String): Int {
        return when (category) {
            Category.TAKOYAKI.serverName -> R.drawable.ic_map_marker_tako
            Category.FISHBREAD.serverName -> R.drawable.ic_map_marker_bungeoppang
            Category.FRUIT.serverName -> R.drawable.ic_map_marker_apple
            Category.CHESTNUTS.serverName -> R.drawable.ic_map_marker_gunbam
            Category.SNACK.serverName -> R.drawable.ic_map_marker_sundae
            Category.GOGUMA.serverName -> R.drawable.ic_map_marker_goguma
            else -> R.drawable.ic_map_marker_tako
        }
    }

    private fun callHomeDataAPI(latitude: Double, longitude: Double, categories: ArrayList<String>?) = viewModel.requestHomeData(latitude, longitude,  categories)

    inner class MarkerEventListener(val context: Context): MapView.POIItemEventListener {
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) { }

        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
            val intent = Intent(context, TruckDetailActivity::class.java).apply {
                putExtra("store_id", p1?.tag)
            }
            Log.d("kimchohee마커", p1?.tag.toString())
            // DaumMap does not support that two or more net.daum.mf.map.api.MapView objects exists at the same time
            // 위 에러 대응을 위해 clMap 뷰에 추가된 지도 뷰를 제거한다.
            binding.clMap.removeView(kakaoMap)
            context.startActivity(intent)
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) { }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) { }
    }
}