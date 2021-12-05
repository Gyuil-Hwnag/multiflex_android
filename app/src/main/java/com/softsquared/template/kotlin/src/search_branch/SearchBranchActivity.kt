package com.softsquared.template.kotlin.src.search_branch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.util.FusedLocationSource
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.*
import com.softsquared.template.kotlin.src.location.LocationActivity
import java.io.IOException
import java.util.*
import com.naver.maps.geometry.LatLng

import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay

class SearchBranchActivity : BaseActivity<ActivitySearchBranchBinding>(ActivitySearchBranchBinding::inflate),
    OnMapReadyCallback,
    NaverMap.OnCameraChangeListener, NaverMap.OnCameraIdleListener, Overlay.OnClickListener {
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


        // 카메라가 멈출시


//         카메라 위치 이동후 경도, 위도
//        binding.registerBtn.setOnClickListener {
//            var cameraPosition = naverMap.cameraPosition
////            var intent = Intent(this, LocationDetailActivity::class.java)
////            intent.putExtra("location", binding.locMainTxt.text)
////            intent.putExtra("latitude", cameraPosition.target.latitude.toString())
////            intent.putExtra("longitude", cameraPosition.target.longitude.toString())
//            Log.d("position!!","대상 지점 위도: " + cameraPosition.target.latitude + ", " +
//                    "대상 지점 경도: " + cameraPosition.target.longitude )
////            startActivity(intent)
//        }

    }

    /*
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // request code와 권한획득 여부 확인
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow)
            }
        }
    }
     */

    //위치정보 권한 설정
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
        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        this.naverMap = naverMap
        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        naverMap.locationSource = locationSource
        naverMap.locationTrackingMode = LocationTrackingMode.Follow

        val uiSettings = naverMap.uiSettings
        uiSettings.isCompassEnabled = true // 나침반
        uiSettings.isScaleBarEnabled = true // 거리
        uiSettings.isZoomControlEnabled = true // 줌
        uiSettings.isLocationButtonEnabled = true // 내가 있는곳
        // 지도상에 마커 표시
        val marker1 = Marker()
        marker1.position = LatLng(37.50133795399799, 127.02662695566022)
        marker1.tag = "강남점 : 서울 강남구 강남대로 432 점프밀라노 5층"
        marker1.onClickListener = this
        marker1.map = naverMap


        val marker2 = Marker()
        marker2.position = LatLng(37.57119997713162, 126.991208952328)
        marker2.tag = "종로점 : 서울 종로구 돈화문로5가길 1 피카디리플러스"
        marker2.onClickListener = this
        marker2.map = naverMap

        val marker3 = Marker()
        marker3.position = LatLng(37.484763562204584, 126.9817715662748)
        marker3.tag = "이수점 : 서울 동작구 동작대로 89 골든시네마타워"
        marker3.onClickListener = this
        marker3.map = naverMap

        val marker4 = Marker()
        marker4.position = LatLng(37.49103068441777, 126.72500175380591)
        marker4.tag = "부평점 : 인천 부평구 경원대로 1404 그랑프리빌딩 3층"
        marker4.onClickListener = this
        marker4.map = naverMap

        val marker5 = Marker()
        marker5.position = LatLng(37.442372333169416, 126.70147813634865)
        marker5.tag = "인천터미널점 : 인천 미추홀구 연남로 35 롯데백화점 지하 1층"
        marker5.onClickListener = this
        marker5.map = naverMap

        val marker6 = Marker()
        marker6.position = LatLng(36.351257688224436, 127.43762536093358)
        marker6.tag = "대전복합터미널점 : 대전 동구 동서대로 1689 대전복합터미널 동관 3층"
        marker6.onClickListener = this
        marker6.map = naverMap

        val marker7 = Marker()
        marker7.position = LatLng(36.34733209431455, 127.38800212679253)
        marker7.tag = "대전서구점 : 대전 서구 문정로 77 로데오타운 5층 메가박스 대전점"
        marker7.onClickListener = this
        marker7.map = naverMap

        val marker8 = Marker()
        marker8.position = LatLng(37.40492429713326, 127.11260236219952)
        marker8.tag = "용인점 : 경기 용인시 수지구 광교중앙로295번길 9 W스퀘어 2층"
        marker8.onClickListener = this
        marker8.map = naverMap

        val marker9 = Marker()
        marker9.position = LatLng(37.318031166822884, 126.83560321147378)
        marker9.tag = "안산점 : 경기 안산시 단원구 고잔2길 41"
        marker9.onClickListener = this
        marker9.map = naverMap

        val marker10 = Marker()
        marker10.position = LatLng(37.26586552436686, 127.00032432681745)
        marker10.tag = "수원AK플라자점 : 경기 수원시 팔달구 덕영대로 924"
        marker10.onClickListener = this
        marker10.map = naverMap

        val marker11 = Marker()
        marker11.position = LatLng(36.635390007695165, 127.48807069029156)
        marker11.tag = "청주성안길점 : 충북 청주시 상당구 상당로81번길 33 쥬네쓰"
        marker11.onClickListener = this
        marker11.map = naverMap

        val marker12 = Marker()
        marker12.position = LatLng(36.83253839998866, 127.14787312680558)
        marker12.tag = "천안두정점 : 충남 천안시 서북구 1공단1길 52 센트하임프라자"
        marker12.onClickListener = this
        marker12.map = naverMap

        val marker13 = Marker()
        marker13.position = LatLng(35.82055707025647, 127.14208718445121)
        marker13.tag = "전주객사점 : 전북 전주시 완산구 전주객사3길 72"
        marker13.onClickListener = this
        marker13.map = naverMap

        val marker14 = Marker()
        marker14.position = LatLng(35.14959971504021, 129.0648996132697)
        marker14.tag = "부산서면점 : 부산 부산진구 동천로 4"
        marker14.onClickListener = this
        marker14.map = naverMap

        val marker15 = Marker()
        marker15.position = LatLng(35.16333063694064, 129.15864979396028)
        marker15.tag = "부산해운대점 : 부산 해운대구 해운대로 620 해운대 라뮤에뜨 2층"
        marker15.onClickListener = this
        marker15.map = naverMap

        //맵 위치 변경시 리스너
        naverMap.addOnCameraChangeListener(this)
        naverMap.addOnCameraIdleListener(this)

        var cameraPosition = naverMap.cameraPosition
        naverMap.addOnCameraIdleListener {
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("현재 주소 : "+address)
//            binding.locMainTxt.setText(address)
//            binding.locSubTxt.setText(address)
        }
    }

    override fun onClick(overlay: Overlay): Boolean {
        showCustomToast(overlay.tag.toString())
        return true
    }

    //지도가 이동시에 이동중임을 확인
    override fun onCameraChange(reason: Int, animated: Boolean) {
        mIsCameraAnimated = animated
    }

    //지도가 멈춘 위치의 좌표로 API로 URL post
    override fun onCameraIdle() {
        if (mIsCameraAnimated) {
            val cameraPosition = naverMap.getCameraPosition()
            var address = getAddress(this, cameraPosition.target.latitude, cameraPosition.target.longitude)
//            showCustomToast("현재 주소 : "+address)
//            binding.locMainTxt.text = address
//            binding.locSubTxt.text = address
//            binding.locMainTxt.setText(address.toString())
//            binding.locSubTxt.setText(address.toString())
        }
    }

    fun getAddress(mContext: Context?, lat: Double, lng: Double): String? {
        var nowAddr = "현재 위치를 확인 할 수 없습니다."
        val geocoder = Geocoder(mContext, Locale.KOREA)
        val address: List<Address>?
        try {
            if (geocoder != null) {
                // 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받고
                // 세번째 파라메터인 maxResults는 리턴받을 주소의 최대 갯수를 지정함
                // (여기서는 1개만 받는걸로...)
                address = geocoder.getFromLocation(lat, lng, 1)
                if (address != null && address.size > 0) {
                    // 주소 받아오기
                    nowAddr = address[0].getAddressLine(0).toString()
                }
            }
        } catch (e: IOException) {
            Toast.makeText(mContext, "주소를 가져 올 수 없습니다.", Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
        return nowAddr
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1000
    }
}
