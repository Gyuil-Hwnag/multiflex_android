package com.softsquared.template.kotlin.src.main.home.bottom_sheet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.user.UserApiClient
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.login.service.PostLoginService
import com.softsquared.template.kotlin.src.register.RegisterActivity
import com.softsquared.template.kotlin.src.main.MainActivity




class BottomSheet() : BottomSheetDialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
//                showCustomToast("토큰 정보 보기 실패")
//                Log.d("kakao_error", error.toString())
            }
            else if (tokenInfo != null) {
//                Log.d("kakao_token", tokenInfo.toString())
//                Toast.makeText(this, "토큰 정보 보기 성공", Toast.LENGTH_SHORT).show()
//                val intent = Intent(this, BirthActivity::class.java)
//                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
//                finish()
            }
        }

        view?.findViewById<TextView>(R.id.register_btn)?.setOnClickListener {
            dismiss()
            var intent = Intent(context, RegisterActivity::class.java)
            startActivity(intent)
        }

        view?.findViewById<Button>(R.id.login1)?.setOnClickListener {
            dismiss()
            var intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.e("response!!", error.toString())
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Log.d("response!!", "접근이 거부 됨(동의 취소)")
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "유효하지 않은 앱")
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
//                        Toast.makeText(this, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태")
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
//                        Toast.makeText(this, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태1")
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
//                        Toast.makeText(this, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태2")
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
//                        Toast.makeText(this, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태3")
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
//                        Toast.makeText(this, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태4")
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
//                        Toast.makeText(this, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태5")
                    }
                    else -> { // Unknown
//                        Toast.makeText(this, "기타 에러", Toast.LENGTH_SHORT).show()
                        Log.d("response!!", "인증 수단이 유효하지 않아 인증할 수 없는 상태6")
                    }
                }
            }
            else if (token != null) {
//                Toast.makeText(this, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                var idToken = token.accessToken
//                var refresh = token.refreshToken

                // token 저장

                Log.d("kakao_token1", token.toString())
                Log.d("oauth_token", OAuthToken.toString())
                dismiss()
                var intent = Intent(context, RegisterActivity::class.java)
                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
        }

        view?.findViewById<ConstraintLayout>(R.id.login2)?.setOnClickListener {
            if(UserApiClient.instance.isKakaoTalkLoginAvailable(context!!)){
                UserApiClient.instance.loginWithKakaoTalk(context!!, callback = callback)
            }else {
                UserApiClient.instance.loginWithKakaoAccount(context!!, callback = callback)

            }
        }
    }
}