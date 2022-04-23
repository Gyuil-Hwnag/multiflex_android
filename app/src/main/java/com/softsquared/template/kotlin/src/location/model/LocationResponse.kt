package com.softsquared.template.kotlin.src.location.model

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.src.location.Location

data class LocationResponse(
    @SerializedName("code") val code: Int,
    @SerializedName("isSuccess") val isSuccess: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("result") val result: List<Location>
)