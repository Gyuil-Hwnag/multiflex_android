package com.softsquared.template.kotlin.src.movieSearch.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    //@SerialzedName : JSON에서 데이터에 매칭되는 이름 명시
    //@Expose : 해당값이 null일경우 json으로 만들 필드를 자동 생략하겠다! 는 명령어
    @SerializedName("rank")
    var rank: String?,
    @SerializedName("movieNm")
    var movieNm: String?
)