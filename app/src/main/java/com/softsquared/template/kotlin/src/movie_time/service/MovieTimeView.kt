package com.softsquared.template.kotlin.src.movie_time.service

import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.softsquared.template.kotlin.src.movie_time.model.MovieTimeResponse


interface MovieTimeView{
    fun onGetMovieTimeSuccess(response: MovieTimeResponse)
    fun onGetMovieTimeFailure(message: String)
}