package com.softsquared.template.kotlin.src.movie_time.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.src.location.Location
import com.softsquared.template.kotlin.src.movie_time.MovieTime

data class MovieTimeResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<MovieTime>
)