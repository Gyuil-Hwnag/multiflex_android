package com.softsquared.template.kotlin.src.main.home.model.movie.service

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse


interface MovieView{
    fun onGetMovieSuccess(response: MovieResponse)
    fun onGetMovieFailure(message: String)
}