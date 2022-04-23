package com.softsquared.template.kotlin.src.ticketing.service

import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.ticketing.model.SeatResponse

interface SeatView{
    fun onPatchSeatSuccess(response: SeatResponse)
    fun onPatchSeatFailure(message: String)
}