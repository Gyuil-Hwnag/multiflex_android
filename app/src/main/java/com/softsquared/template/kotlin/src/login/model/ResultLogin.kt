package com.softsquared.template.kotlin.src.login.model

import com.google.gson.annotations.SerializedName

data class ResultLogin(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String

)