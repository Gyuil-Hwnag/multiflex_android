package com.softsquared.template.kotlin.src.main.home.event

import com.google.gson.annotations.SerializedName

data class event(
    @SerializedName("eventImageUrl") val eventImageUrl: String,
    @SerializedName("eventCount") val eventCount: Int
)