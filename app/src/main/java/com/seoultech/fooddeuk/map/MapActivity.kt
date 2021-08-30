package com.seoultech.fooddeuk.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.seoultech.fooddeuk.R
import com.seoultech.fooddeuk.databinding.ActivityMapBinding
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
    private val markerClickListener = MarkerEventListener(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    private fun initKakaoMap(latitude: Double, longitude: Double) {
        val kakaoMap = KakaoMap
        kakaoMap.init(this, binding.clMap, latitude, longitude)
        setMarkers()
        KakaoMap.kakaoMapView.setPOIItemEventListener(markerClickListener)
    }

    private fun setMarkers() {
        val kakaoMap = KakaoMap
        // TODO : 나중에 서버에서 받아야할 데이터임
        kakaoMap.addMarker(37.624814, 127.077832, "타코야끼집", 0, R.drawable.ic_map_marker_tako) // 예시
        kakaoMap.addMarker(37.625857, 127.077859, "과일트럭", 1, R.drawable.ic_map_marker_apple) // 예시
        kakaoMap.addMarker(37.625043, 127.077505, "붕어빵", 2, R.drawable.ic_map_marker_bungeoppang) // 예시
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

    class MarkerEventListener(val context: Context): MapView.POIItemEventListener {
        override fun onPOIItemSelected(p0: MapView?, p1: MapPOIItem?) {
            Toast.makeText(context, "${p1?.itemName} 눌림", Toast.LENGTH_SHORT).show()
        }

        override fun onCalloutBalloonOfPOIItemTouched(p0: MapView?, p1: MapPOIItem?) {
            TODO("Not yet implemented")
        }

        override fun onCalloutBalloonOfPOIItemTouched(
            p0: MapView?,
            p1: MapPOIItem?,
            p2: MapPOIItem.CalloutBalloonButtonType?
        ) {
            TODO("Not yet implemented")
        }

        override fun onDraggablePOIItemMoved(p0: MapView?, p1: MapPOIItem?, p2: MapPoint?) {
            TODO("Not yet implemented")
        }
    }
}