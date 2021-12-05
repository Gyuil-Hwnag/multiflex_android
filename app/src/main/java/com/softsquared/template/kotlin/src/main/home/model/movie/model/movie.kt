package com.softsquared.template.kotlin.src.main.home.model.movie.model

import com.google.gson.annotations.SerializedName

data class movie(
    @SerializedName("actors") val actors: String,
    @SerializedName("birth") val birth: String,
    @SerializedName("description") val description: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("movieIdx") val movieIdx: Int,
    @SerializedName("movieImage") val movieImage: String,
    @SerializedName("movieName") val movieName: String,
    @SerializedName("producers") val producers: String,
    @SerializedName("star") val star: Float,
    @SerializedName("summary") val summary: String,
    @SerializedName("time") val time: Int
)