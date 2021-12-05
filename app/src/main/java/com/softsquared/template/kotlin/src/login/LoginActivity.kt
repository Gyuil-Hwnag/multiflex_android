package com.softsquared.template.kotlin.src.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.login.model.Login
import com.softsquared.template.kotlin.src.login.model.LoginResponse
import com.softsquared.template.kotlin.src.login.service.LoginView
import com.softsquared.template.kotlin.src.login.service.PostLoginService
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LoginView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.register.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            var id = binding.email.text.toString()
            var password = binding.password.text.toString()
            var body: Login = Login(id, password)
            PostLoginService(this).tryPostLogin(body)
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        // shared preference
        var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var editor = text.edit()
        editor.putString("X-ACCESS-TOKEN", response.result.jwt)
        editor.putInt("userIdx", response.result.userIdx)
        editor.commit()

        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        Log.d("success111", "success")
    }

    override fun onPostLoginFailure(message: String) {
    }
}