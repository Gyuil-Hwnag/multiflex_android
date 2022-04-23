package com.softsquared.template.kotlin.src.ticketing.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.ticketing.model.*
import com.tintin.topping.kotlin.src.streaming.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostTicketingService(val view: TicketingView) {
    fun tryPostTicketing(ticketingRequest: TicketingRequest){
        val ticketingAPI = ApplicationClass.sRetrofit.create(TicketingAPI::class.java)
        ticketingAPI.postTicketing(ticketingRequest).enqueue(object :
            Callback<TicketingResponse> {
            override fun onResponse(call: Call<TicketingResponse>, response: Response<TicketingResponse>) {
                view.onPostTicketingSuccess(response.body() as TicketingResponse)
            }

            override fun onFailure(call: Call<TicketingResponse>, t: Throwable) {
                view.onPostTicketingFailure(t.message ?: "통신 오류")
            }
        })
    }
}