package com.softsquared.template.kotlin.src.qrcode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityQrscannerBinding
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.login.service.LoginView
import com.softsquared.template.kotlin.src.login.service.PostLoginService
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.zxing.integration.android.IntentIntegrator

import com.google.zxing.integration.android.IntentResult
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.qrcode.model.MyTicketing
import com.softsquared.template.kotlin.src.qrcode.model.MyTicketingResponse
import com.softsquared.template.kotlin.src.qrcode.service.MyTicketingService
import com.softsquared.template.kotlin.src.qrcode.service.MyTicketingView


class QrScannerActivity : BaseActivity<ActivityQrscannerBinding>(ActivityQrscannerBinding::inflate), MyTicketingView{

    // movie
    lateinit var myTicketingAdapter: MyTicketingAdapter
    lateinit var mRecyclerView: RecyclerView
    var myticketingList = ArrayList<MyTicketing>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 가로모드
//        IntentIntegrator(this).initiateScan()

        // 세로모드
        var qrScan = IntentIntegrator(this)
        qrScan.setOrientationLocked(true); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.initiateScan();

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
                // todo
            } else {
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                Log.d("response!!", "successs")
                var userIdx = result.contents.toInt()
                MyTicketingService(this).tryGetMyTicketing(userIdx)
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onGetMyTicketingSuccess(response: MyTicketingResponse) {
        /*
        @SerializedName("userIdx") val userIdx: Int,
        @SerializedName("movieName") val movieName: String,
        @SerializedName("theaterName") val theaterName: String,
        @SerializedName("startTime") val startTime: String,
        @SerializedName("count") var count: Int,
        @SerializedName("price") var price: Int,
        @SerializedName("idx") var idx: Int
        */
        Log.d("response!!", "successs")
        var result_response = response.result
        for(i in 0..result_response.size-1){
            var  result = result_response.get(i)
            var userIdx = result.userIdx
            var movieName = result.movieName
            var theaterName = result.theaterName
            var startTime = result.startTime
            var count = result.count
            var price = result.price
            var idx = result.idx

            var body = MyTicketing(userIdx, movieName, theaterName, startTime, count, price, idx)
            myticketingList.add(body)
        }
        myTicketingAdapter = MyTicketingAdapter(myticketingList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = myTicketingAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onGetMyTicketingFailure(message: String) {
    }
}