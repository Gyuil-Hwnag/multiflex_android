package com.softsquared.template.kotlin.src.main.home.model.event.service

import com.softsquared.template.kotlin.src.main.home.model.event.model.EventResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse


interface EventView{
    fun onGetEventSuccess(response: EventResponse)
    fun onGetEventFailure(message: String)
}