package com.softsquared.template.kotlin.src.ticketing.model

import com.google.gson.annotations.SerializedName

data class CurrentSeat(
    @SerializedName("movietimeIdx") var movietimeIdx: Int,
    @SerializedName("seatIdx") var seatIdx: Int,
    @SerializedName("userIdx") var userIdx: Int,
    var is_check: Boolean = false
)

/*
            "movietimeIdx": 1,
            "seatIdx": 1,
            "userIdx": 7
*/