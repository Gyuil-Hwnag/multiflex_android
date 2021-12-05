package com.softsquared.template.kotlin.src.location

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityLocationBinding
import com.softsquared.template.kotlin.databinding.ActivityLoginBinding
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity

class LocationActivity : BaseActivity<ActivityLocationBinding>(ActivityLocationBinding::inflate) {

    // movie
    lateinit var locationAdapter: LocationAdapter
    lateinit var mRecyclerView: RecyclerView
    var locationList = ArrayList<Location>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // movie
        for(i in 0..2){
            locationList.add(Location("서울 충무로 지점"))
            locationList.add(Location("경기 일산지점"))
        }
        locationAdapter = LocationAdapter(locationList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = locationAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)

        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            val intent = Intent() //startActivity()를 할것이 아니므로 그냥 빈 인텐트로 만듦
            for(i in 0..locationList.size-1){
                if(locationList.get(i).is_check){
                    var data = locationList.get(i).locationName
                    intent.putExtra("branch", data)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }

    }
}