package com.softsquared.template.kotlin.src.main.myPage.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.main.myPage.model.MypageResponse
import com.tintin.topping.kotlin.src.streaming.model.MovieAPI
import com.tintin.topping.kotlin.src.streaming.model.MypageAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMypageService(val view: MypageView) {
    fun tryGetMypage(userIdx: Int){
        val mypageAPI = ApplicationClass.sRetrofit.create(MypageAPI::class.java)
        mypageAPI.getMypage(userIdx).enqueue(object :
            Callback<MypageResponse> {
            override fun onResponse(call: Call<MypageResponse>, response: Response<MypageResponse>) {
                view.onGetMyPageSuccess(response.body() as MypageResponse)
            }

            override fun onFailure(call: Call<MypageResponse>, t: Throwable) {
                view.onGetMyPageFailure(t.message ?: "통신 오류")
            }
        })
    }
}