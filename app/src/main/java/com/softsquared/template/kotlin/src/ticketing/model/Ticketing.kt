package com.softsquared.template.kotlin.src.ticketing.model

import com.google.gson.annotations.SerializedName

data class Ticketing(
    @SerializedName("idx") var idx: Int,
)

/*
            "movietimeIdx": 1,
            "seatIdx": 1,
            "userIdx": 7
*/