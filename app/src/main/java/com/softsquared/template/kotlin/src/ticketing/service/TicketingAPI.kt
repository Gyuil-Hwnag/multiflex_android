package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse
import com.softsquared.template.kotlin.src.ticketing.model.*
import retrofit2.Call
import retrofit2.http.*

interface TicketingAPI {
    @POST("/app/tickets/post")
    fun postTicketing(@Body ticketingRequest: TicketingRequest): Call<TicketingResponse>
}