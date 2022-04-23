package com.softsquared.template.kotlin.src.movie_time

import com.google.gson.annotations.SerializedName

data class MovieTime(
    @SerializedName("movieIdx") val movieIdx: Int,
    @SerializedName("theaterIdx") val theaterIdx: Int,
    @SerializedName("showdate") val showdate: String,
    @SerializedName("startTime") val startTime: String,
    @SerializedName("endTime") var endTime: String,
    @SerializedName("idx") var idx: Int,
    var is_check: Boolean = false
)

/*
            "movieIdx": 1,
            "theaterIdx": 8,
            "showdate": "2021-12-10",
            "startTime": "11:00:00",
            "endTime": "13:00:00",
            "idx": 22
*/