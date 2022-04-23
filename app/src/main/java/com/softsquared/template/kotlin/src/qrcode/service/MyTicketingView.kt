package com.softsquared.template.kotlin.src.qrcode.service

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.qrcode.model.MyTicketingResponse


interface MyTicketingView{
    fun onGetMyTicketingSuccess(response: MyTicketingResponse)
    fun onGetMyTicketingFailure(message: String)
}