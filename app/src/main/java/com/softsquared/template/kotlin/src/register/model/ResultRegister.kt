package com.softsquared.template.kotlin.src.register.model

import com.google.gson.annotations.SerializedName

data class ResultRegister(
    @SerializedName("jwt") val jwt: String,
    @SerializedName("userIdx") val userIdx: Int
)