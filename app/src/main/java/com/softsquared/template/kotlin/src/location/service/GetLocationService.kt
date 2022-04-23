package com.softsquared.template.kotlin.src.location.service

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.movie_detail.model.MovieIdxResponse
import com.tintin.topping.kotlin.src.streaming.model.LocationAPI
import com.tintin.topping.kotlin.src.streaming.model.MovieIdxAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetLocationService(val view: LocationView) {
    fun tryGetLocation(){
        val locationAPI = ApplicationClass.sRetrofit.create(LocationAPI::class.java)
        locationAPI.getLocation().enqueue(object :
            Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                view.onGetLocationSuccess(response.body() as LocationResponse)
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                view.onGetLocationFailure(t.message ?: "통신 오류")
            }
        })
    }
}