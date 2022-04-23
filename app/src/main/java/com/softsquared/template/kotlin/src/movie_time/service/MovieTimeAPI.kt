package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse
import retrofit2.Call
import retrofit2.http.*

interface MovieTimeAPI {
    @GET("/app/movietimes")
    fun getLocation(@Query("showdate") showdate:String, @Query("movieIdx") movieIdx: Int,
                    @Query("branchIdx") branchIdx: Int): Call<MovieTimeResponse>
}