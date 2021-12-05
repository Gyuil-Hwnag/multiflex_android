package com.softsquared.template.kotlin.src.movieSearch.model

import com.google.gson.annotations.SerializedName

data class BoxOfficeResult(
    val boxofficeType: String,
    val dailyBoxOfficeList: List<DailyBoxOffice>,
    val showRange: String
)