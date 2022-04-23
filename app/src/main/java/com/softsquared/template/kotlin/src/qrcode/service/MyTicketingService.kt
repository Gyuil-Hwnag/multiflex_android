package com.softsquared.template.kotlin.src.qrcode.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.qrcode.model.MyTicketingResponse
import com.tintin.topping.kotlin.src.streaming.model.LocationAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieIdxAPI
import com.tintin.topping.kotlin.src.streaming.model.MyTicketingAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTicketingService(val view: MyTicketingView) {
    fun tryGetMyTicketing(userIdx: Int){
        val myTicketingAPI = ApplicationClass.sRetrofit.create(MyTicketingAPI::class.java)
        myTicketingAPI.getMyTicketing(userIdx).enqueue(object :
            Callback<MyTicketingResponse> {
            override fun onResponse(call: Call<MyTicketingResponse>, response: Response<MyTicketingResponse>) {
                view.onGetMyTicketingSuccess(response.body() as MyTicketingResponse)
            }

            override fun onFailure(call: Call<MyTicketingResponse>, t: Throwable) {
                view.onGetMyTicketingFailure(t.message ?: "통신 오류")
            }
        })
    }
}