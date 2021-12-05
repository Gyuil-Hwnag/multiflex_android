package com.softsquared.template.kotlin.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.src.main.myPage.model.MypageResponse
import com.softsquared.template.kotlin.src.main.myPage.service.GetMypageService
import com.softsquared.template.kotlin.src.main.myPage.service.MypageView
import com.softsquared.template.kotlin.src.map.MapActivity
import com.softsquared.template.kotlin.src.search_branch.SearchBranchActivity

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page), MypageView {
    private var mCount = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var text = context!!.getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", AppCompatActivity.MODE_PRIVATE)
        var userIdx = text.getInt("userIdx", 0)
        GetMypageService(this).tryGetMypage(userIdx)

        // 로그아웃
        binding.layout9.setOnClickListener {
            var intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }
        // 나의 위치 조정
        binding.layout1.setOnClickListener {
            var intent = Intent(context, SearchBranchActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onGetMyPageSuccess(response: MypageResponse) {
        binding.userNameTxt.setText(response.result.userName)
        binding.userPhoneTxt.setText(response.result.address)
    }

    override fun onGetMyPageFailure(message: String) {
        TODO("Not yet implemented")
    }
}