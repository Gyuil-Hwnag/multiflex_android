package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.main.home.model.event.model.EventResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EventAPI {
    @GET("/app/events")
    fun getEvent(): Call<EventResponse>
}
