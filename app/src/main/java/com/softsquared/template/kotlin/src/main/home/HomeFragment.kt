package com.softsquared.template.kotlin.src.main.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.calender.CalenderActivity
import com.softsquared.template.kotlin.src.location.LocationActivity
import com.softsquared.template.kotlin.src.main.home.event.EventAllAdapter
import com.softsquared.template.kotlin.src.main.home.event.SliderAdapter
import com.softsquared.template.kotlin.src.main.home.event.event
import com.softsquared.template.kotlin.src.main.home.model.event.model.EventResponse
import com.softsquared.template.kotlin.src.main.home.model.event.service.EventView
import com.softsquared.template.kotlin.src.main.home.model.event.service.GetEventService
import com.softsquared.template.kotlin.src.main.home.model.movie.model.MovieResponse
import com.softsquared.template.kotlin.src.main.home.model.movie.service.GetMovieService
import com.softsquared.template.kotlin.src.main.home.model.movie.service.MovieView
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.main.home.movie.movieAdapter
import com.softsquared.template.kotlin.src.map.MapActivity
import com.softsquared.template.kotlin.src.search_branch.SearchBranchActivity

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home)
    , MovieView, EventView{

    // event
    var eventList = ArrayList<event>()
    lateinit var eventAllAdapter: EventAllAdapter
    lateinit var eventRecyclerView: RecyclerView
    var sliderItems: MutableList<String> = ArrayList()
    private var sliderHandler: Handler = Handler()

    // movie
    lateinit var movieAdapter: movieAdapter
    lateinit var mRecyclerView: RecyclerView
    var movieList = ArrayList<movie>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GetMovieService(this).tryGetMovie()
        GetEventService(this).tryGetEvent()


        binding.dayMovie.setOnClickListener {
            var intent = Intent(context, CalenderActivity::class.java)
            startActivity(intent)
        }

        binding.seeBranch.setOnClickListener {
            var intent = Intent(context, SearchBranchActivity::class.java)
            startActivity(intent)
        }

        binding.changeAddress.setOnClickListener {
            var intent = Intent(context, MapActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private val sliderRunnable =
        Runnable { binding.vpImageSlider.setCurrentItem(binding.vpImageSlider.getCurrentItem() + 1) }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

    override fun onGetMovieSuccess(response: MovieResponse) {
        // movie
        var result = response.result
        for(i in 0..result.size-1){
            var img = result.get(i).movieImage
            var name = result.get(i).movieName
            movieList.add(movie(img, name))
        }
        movieAdapter = movieAdapter(movieList)
        mRecyclerView = binding.movieRecycelr
        mRecyclerView.adapter = movieAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,
            false)
    }

    override fun onGetMovieFailure(message: String) {
        TODO("Not yet implemented")
    }

    override fun onGetEventSuccess(response: EventResponse) {
        // event
        var result = response.result
        for(i in 0..result.size-1){
//            Log.d("slider", "")
            sliderItems.add(result.get(i).eventImg)
        }

        binding.vpImageSlider.setAdapter(SliderAdapter(context!!, binding.vpImageSlider, sliderItems))
        binding.vpImageSlider.setClipToPadding(false)
        binding.vpImageSlider.setClipChildren(false)
        binding.vpImageSlider.setOffscreenPageLimit(3)
        binding.vpImageSlider.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
//            page.scaleY = 0.85f + r * 0.15f
        }
        binding.vpImageSlider.setPageTransformer(compositePageTransformer)
        binding.vpImageSlider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 2000)
            }
        })

    }

    override fun onGetEventFailure(message: String) {
        TODO("Not yet implemented")
    }

}