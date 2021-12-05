package com.softsquared.template.kotlin.src.ticketing.model

data class Seat(
    var seat_num: Int,
    var is_check: Boolean = false
)