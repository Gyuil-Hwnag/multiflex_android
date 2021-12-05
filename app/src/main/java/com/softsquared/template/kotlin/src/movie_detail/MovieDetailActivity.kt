package com.softsquared.template.kotlin.src.movie_detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityMovieDetailBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_detail.service.GetMovieIdxService
import com.softsquared.template.kotlin.src.movie_detail.service.MovieIdxView
import com.softsquared.template.kotlin.src.ticketing.TicketingActivity

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>(ActivityMovieDetailBinding::inflate), MovieIdxView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var movieIdx = intent.getIntExtra("movieIdx", 0)+1
        Log.d("movieIdx", movieIdx.toString())

        GetMovieIdxService(this).tryGetMovie(movieIdx)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.register.setOnClickListener {
            var intent = Intent(this, TicketingActivity::class.java)
            intent.putExtra("movieIdx", movieIdx)
            startActivity(intent)
        }

    }

    override fun onGetMovieIdxSuccess(response: MovieIdxResponse) {
        var result = response.result
        binding.movieTime.setText(result.birth)
        binding.movieNameTool.setText(result.movieName)
        binding.actors.setText(result.actors)
        binding.producer.setText(result.producers)
        binding.summary.setText(result.summary)
        binding.movieStar.setText(result.star.toString())
        binding.description.setText(result.description)

        Glide.with(this)
            .load(result.movieImage)
            .transform(FitCenter(), RoundedCorners(10))
            .into(binding.movieImg)
    }

    override fun onGetMovieIdxFailure(message: String) {
    }
}