package com.softsquared.template.kotlin.src.ticketing.model

import com.google.gson.annotations.SerializedName

data class TicketingRequest(
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("movietimeIdx") val movietimeIdx: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("price") val price: Int,
    @SerializedName("status") val status: Int
)

/*
{
    "userIdx": 1,
    "movietimeIdx": 1,
    "count": 1,
    "price": 1,
    "status": 1
}
*/