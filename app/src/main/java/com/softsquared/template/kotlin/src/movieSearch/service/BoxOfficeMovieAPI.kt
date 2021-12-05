package com.softsquared.template.kotlin.src.movieSearch.service

import com.softsquared.template.kotlin.src.movieSearch.model.BoxOfficeResult
import com.softsquared.template.kotlin.src.movieSearch.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BoxOfficeMovieAPI {
    @GET("https://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json")
    fun getMovieList(
        @Query("targetDt") targetDt: String?,
        @Query("key") key: String?
    ): retrofit2.Call<MovieResponse>
}