package com.softsquared.template.kotlin.src.location.service

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse


interface LocationView{
    fun onGetLocationSuccess(response: LocationResponse)
    fun onGetLocationFailure(message: String)
}