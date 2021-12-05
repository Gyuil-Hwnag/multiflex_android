package com.softsquared.template.kotlin.src.movie_detail.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieIdxAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMovieIdxService(val view: MovieIdxView) {
    fun tryGetMovie(movieIdx: Int){
        val movieIdxAPI = ApplicationClass.sRetrofit.create(MovieIdxAPI::class.java)
        movieIdxAPI.getMovie(movieIdx).enqueue(object :
            Callback<MovieIdxResponse> {
            override fun onResponse(call: Call<MovieIdxResponse>, response: Response<MovieIdxResponse>) {
                view.onGetMovieIdxSuccess(response.body() as MovieIdxResponse)
            }

            override fun onFailure(call: Call<MovieIdxResponse>, t: Throwable) {
                view.onGetMovieIdxFailure(t.message ?: "통신 오류")
            }
        })
    }
}