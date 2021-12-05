package com.softsquared.template.kotlin.src.login.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.tintin.topping.kotlin.src.streaming.model.LoginAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import com.tintin.topping.kotlin.src.streaming.model.RegisterAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostLoginService(val view: LoginView) {
    fun tryPostLogin(login: Login){
        val loginAPI = ApplicationClass.sRetrofit.create(LoginAPI::class.java)
        loginAPI.postLogin(login).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                view.onPostLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                view.onPostLoginFailure(t.message ?: "통신 오류")
            }
        })
    }
}