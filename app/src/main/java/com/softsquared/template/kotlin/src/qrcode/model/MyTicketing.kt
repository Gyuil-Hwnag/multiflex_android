package com.softsquared.template.kotlin.src.qrcode.model

import com.google.gson.annotations.SerializedName

data class MyTicketing(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("movieName") val movieName: String,
    @SerializedName("theaterName") val theaterName: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("count") var count: Int,
    @SerializedName("price") var price: Int,
    @SerializedName("idx") var idx: Int
)

/*
            "userIdx": 1,
            "movieName": "듄",
            "theaterName": "1관",
            "startTime": "10:00:00",
            "count": 1,
            "price": 1,
            "idx": 7
*/