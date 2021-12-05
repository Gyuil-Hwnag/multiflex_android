package com.softsquared.template.kotlin.src.register.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import com.tintin.topping.kotlin.src.streaming.model.RegisterAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRegisterService(val view: RegisterView) {
    fun tryPostRegister(register: Register){
        val registerAPI = ApplicationClass.sRetrofit.create(RegisterAPI::class.java)
        registerAPI.postRegister(register).enqueue(object :
            Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                view.onPostRegisterSuccess(response.body() as RegisterResponse)
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                view.onPostRegisterFailure(t.message ?: "통신 오류")
            }
        })
    }
}