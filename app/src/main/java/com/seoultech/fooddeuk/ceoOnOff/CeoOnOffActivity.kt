package com.seoultech.fooddeuk.ceoOnOff

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
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
import com.seoultech.fooddeuk.dialog.OnOffConfirmDialog
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
                    // ???????????? ?????? ????????? ??????
                } else {
                    // TODO : ???????????? ?????? ????????? ????????? ????????? ?????? ????????? onRequestPermissionsResult() ?????????
                }
            }
        }
    }

    private fun subscribeViewModel() {
        viewModel.truckOpenOkCode.observe(this, {
            if (it) {
                Toast.makeText(this, "?????? ??????! ????????? ????????? ??? ????????? ???????????????~", Toast.LENGTH_SHORT).show()
                binding.btnOnSettingSave.visibility = View.INVISIBLE
            } else {
                Toast.makeText(this, "???????????????. ????????? ??? ????????? ?????? ????????? ??????????????????.", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.truckOffStatus.observe(this, {
            if (it) {
                binding.layoutOnOffToggle.onOrOff = false
                binding.onOrOff = false
            }
        })
    }

    private fun init() {
        binding.layoutOnOffToggle.onOrOff = true // true = on, false = off (???????????? on??? ?????????)
        binding.onOrOff = true
    }

    private fun setClickListeners() {
        binding.apply {
            layoutOnOffToggle.btnOn.setOnClickListener {
                binding.layoutOnOffToggle.onOrOff = true
                binding.onOrOff = true
            }
            layoutOnOffToggle.btnOff.setOnClickListener {
                OnOffConfirmDialog.newInstance().show(supportFragmentManager, "OnOffConfirmDialog")
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
                checkLocationPermission() // ?????? ?????? ?????? ?????? ??????
                locationProviderClient = LocationServices.getFusedLocationProviderClient(this@CeoOnOffActivity)
                locationProviderClient.lastLocation.addOnSuccessListener { // ????????? ?????? ????????????
                    layoutOnTruckLocationInput.openLocationInput.setText(getAddress(it.latitude, it.longitude))
                    viewModel.currentLatitude = it.latitude
                    viewModel.currentLongitude = it.longitude
                }
            }
        }
    }

    private fun getAddress(latitude: Double, longitude: Double): String {
        var geocoder = Geocoder(this)
        var list: List<Address> = geocoder.getFromLocation(latitude, longitude, 2)

        if(list!=null) {
            if (list.size == 0) {
                Log.e("Reverse GeoCoding", "???????????? ?????????")
                return ""
            }
            else {
                var address = list.get(0).getAddressLine(0).toString().substring(5)
                return address
            }
        }
        return ""
    }

    /**
     * ???????????? ?????? ????????? ??????????????? ?????? ?????? ???,
     * ???????????? ????????? ???????????? ?????? ?????? ?????? ??????
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