package com.softsquared.template.kotlin.src.movie_select

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.softsquared.template.kotlin.databinding.ActivityMovieSelectBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.service.GetMovieService
import com.softsquared.template.kotlin.src.main.home.model.movie.service.MovieView
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_detail.service.GetMovieIdxService
import com.softsquared.template.kotlin.src.movie_detail.service.MovieIdxView
import com.softsquared.template.kotlin.src.ticketing.TicketingActivity

class MovieSelectActivity : BaseActivity<ActivityMovieSelectBinding>(ActivityMovieSelectBinding::inflate), MovieView {

    // movie
    lateinit var movieAdapter: movieselectAdapter
    lateinit var mRecyclerView: RecyclerView
    var movieList = ArrayList<movieselect>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GetMovieService(this).tryGetMovie()
        binding.closeBtn.setOnClickListener {
            finish()
        }
    }

    override fun onGetMovieSuccess(response: MovieResponse) {
        var result = response.result
        for(i in 0..result.size-1){
            var movie = movieselect(result.get(i).movieImage, result.get(i).movieName, result.get(i).star, result.get(i).summary)
            movieList.add(movie)
        }
        movieAdapter = movieselectAdapter(movieList)
        mRecyclerView = binding.recyclerItm
        mRecyclerView.adapter = movieAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onGetMovieFailure(message: String) {
        TODO("Not yet implemented")
    }

}