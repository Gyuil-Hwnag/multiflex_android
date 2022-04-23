package com.softsquared.template.kotlin.src.movie_time

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityMovieTimeBinding
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.location.service.GetLocationService
import com.softsquared.template.kotlin.src.location.service.LocationView
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse
import com.softsquared.template.kotlin.src.movie_time.service.GetMovieTimeService
import com.softsquared.template.kotlin.src.movie_time.service.MovieTimeView
import com.softsquared.template.kotlin.src.register.RegisterActivity

class MovieTimeActivity : BaseActivity<ActivityMovieTimeBinding>(ActivityMovieTimeBinding::inflate), MovieTimeView {

    // movie
    lateinit var movietimeAdapter: MovietimeAdapter
    lateinit var mRecyclerView: RecyclerView
    var movietimeList = ArrayList<MovieTime>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var  branchIdx = intent.getIntExtra("branchIdx", 0)
        var movieIdx= intent.getIntExtra("movieIdx", 0)
        GetMovieTimeService(this).tryGetMovieTime(movieIdx, branchIdx)
        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            val intent = Intent() //startActivity()를 할것이 아니므로 그냥 빈 인텐트로 만듦
            for(i in 0..movietimeList.size-1){
                if(movietimeList.get(i).is_check){
                    var data = movietimeList.get(i).startTime+" ~ "+movietimeList.get(i).endTime
                    var movietimeIdx = movietimeList.get(i).idx
                    intent.putExtra("time", data)
                    intent.putExtra("movietimeIdx", movietimeIdx)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onGetMovieTimeSuccess(response: MovieTimeResponse) {
        /*
        @SerializedName("movieIdx") val movieIdx: Int,
        @SerializedName("theaterIdx") val theaterIdx: Int,
        @SerializedName("showdate") val showdate: String,
        @SerializedName("startTime") val startTime: String,
        @SerializedName("endTime") var endTime: String,
        @SerializedName("idx") var idx: Int,
        */
        var result_response = response.result
        for(i in 0..result_response.size-1){
            var  result = result_response.get(i)
            var movieIdx = result.movieIdx
            var theaterIdx = result.theaterIdx
            var showdate = result.showdate
            var startTime = result.startTime
            var endTime = result.endTime
            var idx = result.idx
            var moviertime = MovieTime(movieIdx, theaterIdx, showdate, startTime, endTime, idx)
            movietimeList.add(moviertime)
        }
        movietimeAdapter = MovietimeAdapter(movietimeList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = movietimeAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onGetMovieTimeFailure(message: String) {
        TODO("Not yet implemented")
    }
}