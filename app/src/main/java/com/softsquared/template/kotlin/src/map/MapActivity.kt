package com.softsquared.template.kotlin.src.map

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityMapBinding
import com.softsquared.template.kotlin.src.location.LocationActivity
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity
import java.io.IOException
import java.util.*

class MapActivity : BaseActivity<ActivityMapBinding>(ActivityMapBinding::inflate),
    OnMapReadyCallback,
    NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener {
    lateinit private var mapView: MapView
    lateinit private var locationSource: FusedLocationSource
    lateinit private var naverMap: NaverMap

    var mnaverMap: NaverMap? = null
    private var mIsCameraAnimated = false
    var mTvPm10: TextView? = null
    var mSido: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mapView = binding.mapView
        mapView!!.onCreate(savedInstanceState)
        mapView!!.getMapAsync(this)
        locationSource = FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE)

        binding.preBtn.setOnClickListener {
            finish()
        }
        // ???????????? ?????????


//         ????????? ?????? ????????? ??????, ??????
//        binding.registerBtn.setOnClickListener {
//            var cameraPosition = naverMap.cameraPosition
////            var intent = Intent(this, LocationDetailActivity::class.java)
////            intent.putExtra("location", binding.locMainTxt.text)
////            intent.putExtra("latitude", cameraPosition.target.latitude.toString())
////            intent.putExtra("longitude", cameraPosition.target.longitude.toString())
//            Log.d("position!!","?????? ?????? ??????: " + cameraPosition.target.latitude + ", " +
//                    "?????? ?????? ??????: " + cameraPosition.target.longitude )
////            startActivity(intent)
//        }

        binding.saveBtn.setOnClickListener {
            var cameraPosition = naverMap.cameraPosition
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
            val intent = Intent() //startActivity()??? ????????? ???????????? ?????? ??? ???????????? ??????
            var data = address
            intent.putExtra("address", data)
            setResult(RESULT_OK, intent)
            finish()
        }

    }

    //???????????? ?????? ??????
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        if (locationSource!!.onRequestPermissionsResult(
                requestCode, permissions, grantResults
            )
        ) {
            return
        }
        super.onRequestPermissionsResult(
            requestCode, permissions, grantResults
        )
    }

    override fun onMapReady(naverMap: NaverMap) {
        this.naverMap = naverMap
        // NaverMap ?????? ????????? NaverMap ????????? ?????? ?????? ??????
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = false // ?????????
        uiSettings.isScaleBarEnabled = false // ??????
        uiSettings.isZoomControlEnabled = false // ???
        uiSettings.isLocationButtonEnabled = true // ?????? ?????????
        naverMap.mapType = NaverMap.MapType.Navi

        // ?????? ??????
        naverMap.isNightModeEnabled = true
        naverMap.isLiteModeEnabled = false

        //??? ?????? ????????? ?????????
        naverMap.addOnCameraChangeListener(this)
        naverMap.addOnCameraIdleListener(this)

        var cameraPosition = naverMap.cameraPosition
        naverMap.addOnCameraIdleListener {
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("?????? ?????? : "+address)
//            binding.locMainTxt.setText(address)
//            binding.locSubTxt.setText(address)
        }
    }

    //????????? ???????????? ??????????????? ??????
    override fun onCameraChange(reason: Int, animated: Boolean) {
        mIsCameraAnimated = animated
        binding.mark.setImageResource(R.drawable.ic_map_moving_preview_rev_1)
    }

    //????????? ?????? ????????? ????????? API??? URL post
    override fun onCameraIdle() {
        if (mIsCameraAnimated) {
            binding.mark.setImageResource(R.drawable.ic_map_mark_adobespark2)
            val cameraPosition = naverMap.getCameraPosition()
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
            showCustomToast("?????? ?????? : "+address)
//            binding.locMainTxt.text = address
//            binding.locSubTxt.text = address
//            binding.locMainTxt.setText(address.toString())
//            binding.locSubTxt.setText(address.toString())
        }
    }

    fun getAddress(mContext: Context?, lat: Double, lng: Double): String? {
        var nowAddr = "?????? ????????? ?????? ??? ??? ????????????."
        val geocoder = Geocoder(mContext, Locale.KOREA)
        val address: List<Address>?
        try {
            if (geocoder != null) {
                // ???????????? ?????? ??????????????? ????????? ????????????????????? ??????????????? ????????????
                // ????????? ??????????????? maxResults??? ???????????? ????????? ?????? ????????? ?????????
                // (???????????? 1?????? ????????????...)
                address = geocoder.getFromLocation(lat, lng, 1)
                if (address != null && address.size > 0) {
                    // ?????? ????????????
                    nowAddr = address[0].getAddressLine(0).toString()
                }
            }
        } catch (e: IOException) {
            Toast.makeText(mContext, "????????? ?????? ??? ??? ????????????.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        return nowAddr
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
