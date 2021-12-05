package com.softsquared.template.kotlin.src.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.calender.CalenderActivity
import com.softsquared.template.kotlin.src.location.LocationActivity
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.bottom_sheet.BottomSheet
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.map.MapActivity
import com.softsquared.template.kotlin.src.movieSearch.MovieSearchActivity
import com.softsquared.template.kotlin.src.movie_select.MovieSelectActivity
import com.softsquared.template.kotlin.src.ticketing.TicketingActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // shared preference
        var text = getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
        var editor = text.edit()
        var login_status_jwt = text.getString("X-ACCESS-TOKEN", null)
        var login_status_userIdx = text.getInt("userIdx", -1)
//        showCustomToast("jwt : "+login_status_jwt)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_register -> {
                        if(login_status_jwt == null){
                            val bottomSheet = BottomSheet()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }
                        else{
                            var intent = Intent(this, MovieSelectActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        if(login_status_jwt == null){
                            val bottomSheet = BottomSheet()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }
                        else{
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frm, MyPageFragment())
                                .commitAllowingStateLoss()
                            return@OnNavigationItemSelectedListener true
                        }
//                        supportFragmentManager.beginTransaction()
//                            .replace(R.id.main_frm, MyPageFragment())
//                            .commitAllowingStateLoss()
//                        return@OnNavigationItemSelectedListener true
//                        var intent = Intent(this, LoginActivity::class.java)
//                        startActivity(intent)
                    }
                }
                false
            })
    }
}