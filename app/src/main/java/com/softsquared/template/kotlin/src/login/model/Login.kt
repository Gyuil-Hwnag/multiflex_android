package com.softsquared.template.kotlin.src.login.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("id") val id: String,
    @SerializedName("password") val password: String
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