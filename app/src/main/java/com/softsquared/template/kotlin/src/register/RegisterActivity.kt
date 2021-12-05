package com.softsquared.template.kotlin.src.register

import android.R.attr
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.databinding.ActivityRegisterBinding
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.map.MapActivity
import android.R.attr.data
import com.softsquared.template.kotlin.src.register.model.Register
import com.softsquared.template.kotlin.src.register.model.RegisterResponse
import com.softsquared.template.kotlin.src.register.service.PostRegisterService
import com.softsquared.template.kotlin.src.register.service.RegisterView

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.registerBtn.setOnClickListener {
            /*
            @SerializedName("userName") val userName: String,
            @SerializedName("id") val id: String,
            @SerializedName("email") val email: String,
            @SerializedName("password") val password: String,
            @SerializedName("birth") val birth: Int,
            @SerializedName("address") val address: String
            */
            var name = binding.name.text.toString()
            var email = binding.email.text.toString()
            var address = binding.address.text.toString()
            var id = binding.id.text.toString()
            var password = binding.password.text.toString()
            var birth = binding.birth.text.toString().toInt()
            var body: Register = Register(name, id, email, password, birth, address)
            PostRegisterService(this).tryPostRegister(body)
        }

        binding.address.setOnClickListener {
            var intent = Intent(this, MapActivity::class.java)
            startActivityForResult(intent, 1234)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1234 && resultCode === RESULT_OK) {
            val myData: String = data!!.getStringExtra("address")!!
            binding.address.setText(myData)
        } else {
//            textView.setText("No Data")
        }
    }

    override fun onPostRegisterSuccess(response: RegisterResponse) {
        finish()
    }

    override fun onPostRegisterFailure(message: String) {
        TODO("Not yet implemented")
    }
}