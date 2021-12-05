package com.softsquared.template.kotlin.src.main.home.event

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R

class EventAllAdapter(private val itemList : ArrayList<event>) :
    RecyclerView.Adapter<EventAllAdapter.ViewHolder>(){
    var datas = ArrayList<event>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var event_img: ImageView = itemView.findViewById(R.id.img_event_all)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_event_all_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.getContext())
            .load(itemList.get(position).eventImageUrl)
            .into(holder.event_img)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}