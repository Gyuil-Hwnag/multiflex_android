package com.softsquared.template.kotlin.src.main.home.model.event.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.model.event.model.EventResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.tintin.topping.kotlin.src.streaming.model.EventAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetEventService(val view: EventView) {
    fun tryGetEvent(){
        val eventAPI = ApplicationClass.sRetrofit.create(EventAPI::class.java)
        eventAPI.getEvent().enqueue(object :
            Callback<EventResponse> {
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                view.onGetEventSuccess(response.body() as EventResponse)
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                view.onGetEventFailure(t.message ?: "통신 오류")
            }
        })
    }
}