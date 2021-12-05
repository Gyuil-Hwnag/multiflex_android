package com.softsquared.template.kotlin.src.login.service

import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.RegisterResponse

interface LoginView{
    fun onPostLoginSuccess(response: LoginResponse)
    fun onPostLoginFailure(message: String)
}