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
import com.softsquared.template.kotlin.src.location.model.LocationResponse
import com.softsquared.template.kotlin.src.location.service.GetLocationService
import com.softsquared.template.kotlin.src.location.service.LocationView
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.register.RegisterActivity

class LocationActivity : BaseActivity<ActivityLocationBinding>(ActivityLocationBinding::inflate), LocationView {

    // movie
    lateinit var locationAdapter: LocationAdapter
    lateinit var mRecyclerView: RecyclerView
    var locationList = ArrayList<Location>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        GetLocationService(this).tryGetLocation()
        binding.closeBtn.setOnClickListener {
            finish()
        }

        binding.saveBtn.setOnClickListener {
            val intent = Intent() //startActivity()를 할것이 아니므로 그냥 빈 인텐트로 만듦
            for(i in 0..locationList.size-1){
                if(locationList.get(i).is_check){
                    var data = locationList.get(i).branchName
                    var branchIdx = locationList.get(i).idx
                    intent.putExtra("branchName", data)
                    intent.putExtra("branchIdx", branchIdx)
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }

    override fun onGetLocationSuccess(response: LocationResponse) {
        /*
        @SerializedName("branchName") val branchName: String,
        @SerializedName("branchAddress") val branchAddress: String,
        @SerializedName("branchTelecom") val branchTelecom: String,
        @SerializedName("locationIdx") val locationIdx: Int,
        @SerializedName("locationName") var locationName: String,
        @SerializedName("idx") var idx: Int,
        */
        var result_response = response.result
        for(i in 0..result_response.size-1){
            var  result = result_response.get(i)
            var branchName = result.branchName
            var branchAddress = result.branchAddress
            var branchTelecom = result.branchTelecom
            var locationIdx = result.locationIdx
            var locationName = result.locationName
            var idx = result.idx
            var location = Location(branchName, branchAddress, branchTelecom, locationIdx, locationName, idx)
            locationList.add(location)
        }
        locationAdapter = LocationAdapter(locationList)
        mRecyclerView = binding.recyclerItem
        mRecyclerView.adapter = locationAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
            false)
    }

    override fun onGetLocationFailure(message: String) {
        TODO("Not yet implemented")
    }
}