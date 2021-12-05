package com.softsquared.template.kotlin.src.main.home.model.event.model

import com.google.gson.annotations.SerializedName

data class event(
    @SerializedName("eventIdx") val eventIdx: Int,
    @SerializedName("eventImg") val eventImg: String,
    @SerializedName("eventName") val eventName: String
)