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
import com.softsquared.template.kotlin.src.ticketing.model.Seat
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.softsquared.template.kotlin.src.location.LocationActivity
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_detail.service.GetMovieIdxService
import com.softsquared.template.kotlin.src.movie_detail.service.MovieIdxView


class TicketingActivity : BaseActivity<ActivityTicketingBinding>(ActivityTicketingBinding::inflate),
    MovieIdxView {

    // movie
    lateinit var seatAdapter: SeatAdapter
    lateinit var mRecyclerView: RecyclerView
    var seatList = ArrayList<Seat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // shared preference
        var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var editor = text.edit()
        var login_status_jwt = text.getString("X-ACCESS-TOKEN", null)
        var login_status_userIdx = text.getInt("userIdx", -1)
//        showCustomToast("jwt : "+login_status_jwt)

        var movieIdx = intent.getIntExtra("movieIdx", 0)
        GetMovieIdxService(this).tryGetMovie(movieIdx)
        // movie
        for(i in 1..20){
            seatList.add(Seat(i))
        }
        seatAdapter = SeatAdapter(seatList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = seatAdapter
        mRecyclerView.layoutManager = GridLayoutManager(this, 5)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.branch.setOnClickListener {
            var intent = Intent(this, LocationActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1234 && resultCode === RESULT_OK) {
            val myData: String = data!!.getStringExtra("branch")!!
            binding.branch.setText(myData)
        } else {
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
}