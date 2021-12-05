package com.softsquared.template.kotlin.src.movieSearch.service

import com.softsquared.template.kotlin.src.movieSearch.model.MovieResponse

interface BoxOfficeMovieView{
    fun onMovieSuccess(response: MovieResponse)
    fun onMovieFailure(message: String)
}