package com.softsquared.template.kotlin.src.ticketing

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityTicketingBinding
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.src.location.LocationActivity
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_detail.service.GetMovieIdxService
import com.softsquared.template.kotlin.src.movie_detail.service.MovieIdxView
import com.softsquared.template.kotlin.src.movie_time.MovieTimeActivity
import com.softsquared.template.kotlin.src.ticketing.model.*
import com.softsquared.template.kotlin.src.ticketing.service.*


class TicketingActivity : BaseActivity<ActivityTicketingBinding>(ActivityTicketingBinding::inflate),
    MovieIdxView, SeatView, CurrentSeatView, TicketingView {

    // movie
    lateinit var seatAdapter: SeatAdapter
    lateinit var mRecyclerView: RecyclerView
    var seatList = ArrayList<CurrentSeat>()
    var branchIdx = 0
    var movietimeIdx = 0
    var count = 0
    var Idx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // shared preference
        var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var editor = text.edit()
        var login_status_jwt = text.getString("X-ACCESS-TOKEN", null)
        var userIdx = text.getInt("userIdx", -1)
        Idx = userIdx
//        showCustomToast("jwt : "+login_status_jwt)

        var movieIdx = intent.getIntExtra("movieIdx", 0)
        GetMovieIdxService(this).tryGetMovie(movieIdx)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.seatOkTxt.setOnClickListener {
            for(i in 0..seatList.size-1){
                if(seatList.get(i).is_check && seatList.get(i).userIdx == 0){
                    count = count+1
                    var seatRequest = SeatRequest(movieIdx,i+1, userIdx)
                    PatchSeatService(this).tryPathchSeat(seatRequest)
                }
            }
        }

        // 지점
        binding.branch.setOnClickListener {
            var intent = Intent(this, LocationActivity::class.java)
            startActivityForResult(intent, 1111)
        }

        // 영화시간
        binding.movieTime.setOnClickListener {
            var intent = Intent(this, MovieTimeActivity::class.java)
            intent.putExtra("movieIdx", movieIdx)
            intent.putExtra("branchIdx", branchIdx)
            startActivityForResult(intent, 2222)
        }

        binding.ticketing.setOnClickListener {
//            @SerializedName("userIdx") val userIdx: Int,
//            @SerializedName("movietimeIdx") val movietimeIdx: Int,
//            @SerializedName("count") val count: Int,
//            @SerializedName("price") val price: Int,
//            @SerializedName("status") val status: Int
            var userIdx = userIdx
            var movietimeIdx = movietimeIdx
            var count = count
            var price = count*10000
            var status = 1
            var body = TicketingRequest(userIdx, movietimeIdx, count, price, status)
            Log.d("response!!", body.toString())
            PostTicketingService(this).tryPostTicketing(body)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1111 && resultCode === RESULT_OK) {
            val myData: String = data!!.getStringExtra("branchName")!!
            binding.branch.setText(myData)
            branchIdx = data!!.getIntExtra("branchIdx", 0)!!
            Log.d("response!!", branchIdx.toString())
        }
        else if(requestCode === 2222 && resultCode === RESULT_OK){
            val time: String = data!!.getStringExtra("time")!!
            Log.d("response!!", time)
            binding.movieTime.setText(time)
            movietimeIdx = data!!.getIntExtra("movietimeIdx", 0)!!
            GetCurrentSeatService(this).tryGetCurrent(movietimeIdx)
            Log.d("response!!", movietimeIdx.toString())

            // 좌석 지정하기


        }else {
//            textView.setText("No Data")
        }
    }

    override fun onGetMovieIdxSuccess(response: MovieIdxResponse) {
        var result = response.result
        binding.movieName.setText(result.movieName)
        binding.movieSummary.setText(result.summary)
        Glide.with(this)
            .load(result.movieImage)
            .transform(FitCenter(), RoundedCorners(10))
            .into(binding.movieImg)
    }

    override fun onGetMovieIdxFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onPatchSeatSuccess(response: SeatResponse) {
        Log.d("response!!", "success")
    }

    override fun onPatchSeatFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetCurrentSeatSuccess(response: CurrentSeatResponse) {
        //            @SerializedName("movietimeIdx") var movietimeIdx: Int,
        //            @SerializedName("seatIdx") var seatIdx: Int,
        //            @SerializedName("userIdx") var userIdx: Int,
        seatList.clear()
        var result = response.result
        for(i in 0..result.size-1){
            var movietimeIdx = result.get(i).movietimeIdx
            var seatIdx = result.get(i).seatIdx
            var userIdx = result.get(i).userIdx
            var body = CurrentSeat(movietimeIdx, seatIdx, userIdx)

            seatList.add(body)
        }
        seatAdapter = SeatAdapter(seatList, this, Idx)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = seatAdapter
        mRecyclerView.layoutManager = GridLayoutManager(this, 5)
    }

    override fun onGetCurrentSeatFailure(message: String) {

    }

    override fun onPostTicketingSuccess(response: TicketingResponse) {
        var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var editor = text.edit()
        editor.putInt("ticketIdx", response.result.idx)
        Log.d("response!!", response.result.idx.toString())
        editor.commit()
        finish()
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPostTicketingFailure(message: String) {
        TODO("Not yet implemented")
    }
}