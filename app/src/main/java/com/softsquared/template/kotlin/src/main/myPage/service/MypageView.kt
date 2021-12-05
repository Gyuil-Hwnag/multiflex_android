package com.softsquared.template.kotlin.src.main.myPage.service

import com.softsquared.template.kotlin.src.main.myPage.model.MypageResponse


interface MypageView{
    fun onGetMyPageSuccess(response: MypageResponse)
    fun onGetMyPageFailure(message: String)
}