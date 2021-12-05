package com.softsquared.template.kotlin.src.register.model

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultRegister
)