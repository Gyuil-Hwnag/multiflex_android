package com.softsquared.template.kotlin.src.main.myPage

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivitySettingBinding
import com.softsquared.template.kotlin.src.main.MainActivity

class SettingActivity : BaseActivity<ActivitySettingBinding>(ActivitySettingBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.logoutBtn.setOnClickListener {
            var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
            var editor = text.edit()
            editor.putString("X-ACCESS-TOKEN", null)
            editor.putInt("userIdx", 0)
            editor.commit()
            finish()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}