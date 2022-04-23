package com.softsquared.template.kotlin.src.ticketing.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.ticketing.model.SeatRequest
import com.softsquared.template.kotlin.src.ticketing.model.SeatResponse
import com.tintin.topping.kotlin.src.streaming.model.LoginAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import com.tintin.topping.kotlin.src.streaming.model.RegisterAPI
import com.tintin.topping.kotlin.src.streaming.model.SeatAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatchSeatService(val view: SeatView) {
    fun tryPathchSeat(seatRequest: SeatRequest){
        val seatAPI = ApplicationClass.sRetrofit.create(SeatAPI::class.java)
        seatAPI.patchSeat(seatRequest).enqueue(object :
            Callback<SeatResponse> {
            override fun onResponse(call: Call<SeatResponse>, response: Response<SeatResponse>) {
                view.onPatchSeatSuccess(response.body() as SeatResponse)
            }

            override fun onFailure(call: Call<SeatResponse>, t: Throwable) {
                view.onPatchSeatFailure(t.message ?: "통신 오류")
            }
        })
    }
}