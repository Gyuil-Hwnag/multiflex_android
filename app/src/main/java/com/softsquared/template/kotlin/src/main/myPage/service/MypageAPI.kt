package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.main.myPage.model.MypageResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MypageAPI {
    @GET("/app/users/{userIdx}")
    fun getMypage(@Path("userIdx") userIdx: Int): Call<MypageResponse>
}
