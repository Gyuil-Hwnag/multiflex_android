package com.softsquared.template.kotlin.src.movie_detail.model

import com.google.gson.annotations.SerializedName

data class MovieIdxResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: movieIdx
)