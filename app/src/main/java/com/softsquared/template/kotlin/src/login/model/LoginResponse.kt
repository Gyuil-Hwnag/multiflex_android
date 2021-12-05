package com.softsquared.template.kotlin.src.login.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultLogin
)