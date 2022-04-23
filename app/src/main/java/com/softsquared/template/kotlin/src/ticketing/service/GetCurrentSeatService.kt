package com.softsquared.template.kotlin.src.ticketing.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.ticketing.model.CurrentSeatResponse
import com.softsquared.template.kotlin.src.ticketing.model.SeatRequest
import com.softsquared.template.kotlin.src.ticketing.model.SeatResponse
import com.tintin.topping.kotlin.src.streaming.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetCurrentSeatService(val view: CurrentSeatView) {
    fun tryGetCurrent(movietimeIdx: Int){
        val currentseatAPI = ApplicationClass.sRetrofit.create(CurrentSeatAPI::class.java)
        currentseatAPI.getCurrentSeat(movietimeIdx).enqueue(object :
            Callback<CurrentSeatResponse> {
            override fun onResponse(call: Call<CurrentSeatResponse>, response: Response<CurrentSeatResponse>) {
                view.onGetCurrentSeatSuccess(response.body() as CurrentSeatResponse)
            }

            override fun onFailure(call: Call<CurrentSeatResponse>, t: Throwable) {
                view.onGetCurrentSeatFailure(t.message ?: "통신 오류")
            }
        })
    }
}