package com.softsquared.template.kotlin.src.main.myPage.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("address") val address: String,
    @SerializedName("birth") val birth: Int,
    @SerializedName("email") val email: String,
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String,
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("userName") val userName: String
)