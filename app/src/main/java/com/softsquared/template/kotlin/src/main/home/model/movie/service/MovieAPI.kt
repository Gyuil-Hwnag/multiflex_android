package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MovieAPI {
    @GET("/app/movie")
    fun getMovie(): Call<MovieResponse>
}
