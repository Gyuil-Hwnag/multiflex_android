package com.softsquared.template.kotlin.src.movieSearch.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.movieSearch.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BoxOfficeMovieService(val view: BoxOfficeMovieView) {
    fun tryMovieList(today: String){
        val boxOfficeMovieAPI = ApplicationClass.sRetrofit.create(BoxOfficeMovieAPI::class.java)
        boxOfficeMovieAPI.getMovieList(today, "7e1755d047263730724e74e0096ebdf2").enqueue(object :
            Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                view.onMovieSuccess(response.body() as MovieResponse)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                view.onMovieFailure(t.message ?: "통신 오류")
            }
        })
    }
}