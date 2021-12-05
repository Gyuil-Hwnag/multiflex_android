package com.softsquared.template.kotlin.src.movie_detail.service

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse


interface MovieIdxView{
    fun onGetMovieIdxSuccess(response: MovieIdxResponse)
    fun onGetMovieIdxFailure(message: String)
}