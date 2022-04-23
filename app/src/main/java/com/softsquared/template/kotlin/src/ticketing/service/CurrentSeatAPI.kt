package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse
import com.softsquared.template.kotlin.src.ticketing.model.CurrentSeatResponse
import com.softsquared.template.kotlin.src.ticketing.model.SeatRequest
import com.softsquared.template.kotlin.src.ticketing.model.SeatResponse
import retrofit2.Call
import retrofit2.http.*

interface CurrentSeatAPI {
    @GET("/app/seats")
    fun getCurrentSeat(@Query("movietimeIdx") movietimeIdx: Int): Call<CurrentSeatResponse>
}