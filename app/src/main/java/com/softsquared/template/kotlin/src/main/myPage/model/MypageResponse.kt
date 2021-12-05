package com.softsquared.template.kotlin.src.main.myPage.model

import com.google.gson.annotations.SerializedName

data class MypageResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: Result
)