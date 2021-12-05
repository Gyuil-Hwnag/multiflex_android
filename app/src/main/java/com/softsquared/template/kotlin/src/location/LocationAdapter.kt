package com.softsquared.template.kotlin.src.location

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.movie_detail.MovieDetailActivity

class LocationAdapter(private val itemList : ArrayList<Location>) :
    RecyclerView.Adapter<LocationAdapter.ViewHolder>(){
    var datas = ArrayList<Location>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var loc_name: TextView = itemView.findViewById<TextView>(R.id.loc_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!itemList.get(position).is_check){
            holder.loc_name.setText(itemList.get(position).locationName)
            holder.loc_name.setBackgroundResource(R.drawable.login_edit_shape)
            holder.loc_name.setTextColor(Color.parseColor("#000000"))
        }
        else{
            holder.loc_name.setText(itemList.get(position).locationName)
            holder.loc_name.setTextColor(Color.parseColor("#FFFFFF"))
            holder.loc_name.setBackgroundResource(R.color.register_login_blue)
        }

        holder.itemView.setOnClickListener {
            if(itemList.get(position).is_check){
                itemList.get(position).is_check = false
                holder.loc_name.setBackgroundResource(R.drawable.login_edit_shape)
                holder.loc_name.setTextColor(Color.parseColor("#000000"))
            }
            else{
                itemList.get(position).is_check = true
                holder.loc_name.setTextColor(Color.parseColor("#FFFFFF"))
                holder.loc_name.setBackgroundResource(R.color.register_login_blue)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}