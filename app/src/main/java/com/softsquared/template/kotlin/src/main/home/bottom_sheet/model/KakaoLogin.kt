package com.softsquared.template.kotlin.src.main.home.bottom_sheet.model

import com.google.gson.annotations.SerializedName

data class KakaoLogin(
    @SerializedName("userName") val userName: String,
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("birth") val birth: Int,
    @SerializedName("address") val address: String
)

/*
{
    "userName" : "황규일",
    "id" : "admin",
    "email" : "admin@naver.com",
    "password" : "1234",
    "birth": 19960608,
    "address": "ilsan"
}
 */