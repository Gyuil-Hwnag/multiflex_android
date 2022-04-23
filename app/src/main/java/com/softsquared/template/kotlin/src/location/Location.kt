package com.softsquared.template.kotlin.src.location

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("branchName") val branchName: String,
    @SerializedName("branchAddress") val branchAddress: String,
    @SerializedName("branchTelecom") val branchTelecom: String,
    @SerializedName("locationIdx") val locationIdx: Int,
    @SerializedName("locationName") var locationName: String,
    @SerializedName("idx") var idx: Int,
    var is_check: Boolean = false
)

/*
"branchName": "대전서구점",
            "branchAddress": "대전 서구 문정로 77 로데오타운 5층 메가박스 대전점",
            "branchTelecom": 3001,
            "locationIdx": 3,
            "locationName": "대전",
            "idx": 7
*/