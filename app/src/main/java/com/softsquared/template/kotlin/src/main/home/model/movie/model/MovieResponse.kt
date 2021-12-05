package com.softsquared.template.kotlin.src.main.home.model.movie.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ArrayList<movie>
)