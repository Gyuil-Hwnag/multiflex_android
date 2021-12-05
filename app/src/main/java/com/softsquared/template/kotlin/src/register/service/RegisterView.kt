package com.softsquared.template.kotlin.src.register.service

import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.RegisterResponse


interface RegisterView{
    fun onPostRegisterSuccess(response: RegisterResponse)
    fun onPostRegisterFailure(message: String)
}