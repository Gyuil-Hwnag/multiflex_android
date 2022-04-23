package com.softsquared.template.kotlin.src.movie_time.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_time.MovieTime
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse
import com.tintin.topping.kotlin.src.streaming.model.LocationAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieIdxAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieTimeAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMovieTimeService(val view: MovieTimeView) {
    fun tryGetMovieTime(movieIdx: Int, branchIdx: Int){
        val movietimeAPI = ApplicationClass.sRetrofit.create(MovieTimeAPI::class.java)
        movietimeAPI.getLocation("2021-12-10",movieIdx, branchIdx ).enqueue(object :
            Callback<MovieTimeResponse> {
            override fun onResponse(call: Call<MovieTimeResponse>, response: Response<MovieTimeResponse>) {
                view.onGetMovieTimeSuccess(response.body() as MovieTimeResponse)
            }

            override fun onFailure(call: Call<MovieTimeResponse>, t: Throwable) {
                view.onGetMovieTimeFailure(t.message ?: "통신 오류")
            }
        })
    }
}