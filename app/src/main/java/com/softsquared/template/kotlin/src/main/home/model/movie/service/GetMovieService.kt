package com.softsquared.template.kotlin.src.main.home.model.movie.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMovieService(val view: MovieView) {
    fun tryGetMovie(){
        val movieAPI = ApplicationClass.sRetrofit.create(MovieAPI::class.java)
        movieAPI.getMovie().enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                view.onGetMovieSuccess(response.body() as MovieResponse)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                view.onGetMovieFailure(t.message ?: "통신 오류")
            }
        })
    }
}