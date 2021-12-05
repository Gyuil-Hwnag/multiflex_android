package com.tintin.topping.kotlin.src.streaming.model

import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.register.model.Register
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LoginAPI {
    @POST("/app/users/logIn")
    fun postLogin(@Body login: Login): Call<LoginResponse>
}
