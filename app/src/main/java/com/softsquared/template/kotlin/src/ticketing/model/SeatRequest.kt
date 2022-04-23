package com.softsquared.template.kotlin.src.ticketing.model

import com.google.gson.annotations.SerializedName

data class SeatRequest(
    @SerializedName("movietimeIdx") val id: Int,
    @SerializedName("seatIdx") val seatIdx: Int,
    @SerializedName("userIdx") val userIdx: Int
)