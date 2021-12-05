package com.softsquared.template.kotlin.src.movieSearch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityMovieSearchBinding
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.movieSearch.model.MovieDto
import com.softsquared.template.kotlin.src.movieSearch.model.MovieResponse
import com.softsquared.template.kotlin.src.movieSearch.service.BoxOfficeMovieService
import com.softsquared.template.kotlin.src.movieSearch.service.BoxOfficeMovieView
import com.softsquared.template.kotlin.src.movieSearch.service.RetrofitBuilder

class MovieSearchActivity : BaseActivity<ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate), BoxOfficeMovieView {

    // movie
    lateinit var movieAdapter: MovieSearchAdapter
    lateinit var mRecyclerView: RecyclerView
    var movieList = ArrayList<MovieDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var today = intent.getStringExtra("today")
        var today2 = intent.getStringExtra("today2")
        if(today == null){

        }
        else{
            binding.toolbarTxt.text = today2
            BoxOfficeMovieService(this).tryMovieList(today!!)
        }

        // movie
//        for(i in 0..2){
//            locationList.add(Location("서울 충무로 지점"))
//            locationList.add(Location("경기 일산지점"))
//        }



        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
//            var intent = Intent(this, )
        }

    }

    override fun onMovieSuccess(response: MovieResponse) {
        var result = response.boxofficeResult.dailyBoxOfficeList
        for(i in 0..result.size-1){
            var rank = result.get(i).rank
            var name = result.get(i).movieNm
            var movie = MovieDto(rank, name)
            movieList.add(movie)
        }
        movieAdapter = MovieSearchAdapter(movieList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = movieAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onMovieFailure(message: String) {
        TODO("Not yet implemented")
    }
}