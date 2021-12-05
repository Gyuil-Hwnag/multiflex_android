package com.softsquared.template.kotlin.src.main.home.event

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.databinding.SlideItemBinding


class SliderAdapter(context: Context, viewPager2: ViewPager2, sliderImage: MutableList<String>) :
    RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    var mContext: Context
    var mViewPager2: ViewPager2
    lateinit var sliderItems: MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        return SliderViewHolder(
            SlideItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position])
        if (position == sliderItems.size - 2) {
            mViewPager2.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    inner class SliderViewHolder(binding: SlideItemBinding) :
        RecyclerView.ViewHolder(binding.getRoot()) {
        private val mBinding: SlideItemBinding
        fun bind(sliderItem: String?) {
            try {
                Glide.with(mContext).load(sliderItem).into(mBinding.imageSlider)
            } catch (e: Exception) {
                Log.d(TAG, "ERROR: " + e.message)
            }
        }

        init {
            mBinding = binding
        }
    }

    private val runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

    companion object {
        private const val TAG = "SliderAdapter"
    }

    init {
        mContext = context
        mViewPager2 = viewPager2
        sliderItems = sliderImage
    }
}