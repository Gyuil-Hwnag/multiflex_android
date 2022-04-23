package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MovieIdxAPI {
    @GET("/app/movie/{movieIdx}")
    fun getMovie(@Path("movieIdx") movieIdx: Int): Call<MovieIdxResponse>
}