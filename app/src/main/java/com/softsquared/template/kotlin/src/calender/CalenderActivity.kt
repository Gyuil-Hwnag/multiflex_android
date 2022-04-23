package com.softsquared.template.kotlin.src.calender

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applikeysolutions.cosmocalendar.utils.SelectionType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCalenderBinding
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.location.LocationAdapter
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.movieSearch.MovieSearchActivity
import com.softsquared.template.kotlin.src.register.RegisterActivity
import java.text.SimpleDateFormat
import java.util.*

class CalenderActivity : BaseActivity<ActivityCalenderBinding>(ActivityCalenderBinding::inflate) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.closeBtn.setOnClickListener {
            finish()
        }

        var calendarView = binding.calendarView

        calendarView.isShowDaysOfWeekTitle = false
        calendarView.setSelectionType(SelectionType.SINGLE)
        var result_day: String = ""
        binding.saveBtn.setOnClickListener {
            val days: List<Calendar> = calendarView.selectedDates
            var result = ""
            var result_day = ""
            for (i in days.indices) {
                val calendar: Calendar = days[i]
                val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
                val month: Int = calendar.get(Calendar.MONTH)
                val year: Int = calendar.get(Calendar.YEAR)
                val week: String = SimpleDateFormat("EE").format(calendar.getTime())
                if(day < 10){
                    result = "0"+day.toString()
                }
                else{
                    result = day.toString()
                }
                val day_full = year.toString()+(month+1).toString()+result
                result_day = day_full
                result = year.toString()+"년 "+(month+1).toString()+"월 "+day.toString()+"일"
            }
//            showCustomToast(result_day)
            Log.d("result_day", result_day)
            var intent = Intent(this, MovieSearchActivity::class.java)
            intent.putExtra("today", result_day)
            intent.putExtra("today2", result)
            startActivity(intent)
        }
    }
}