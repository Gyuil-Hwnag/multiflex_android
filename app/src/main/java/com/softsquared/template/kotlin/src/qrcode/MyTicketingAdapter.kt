package com.softsquared.template.kotlin.src.qrcode

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
import com.softsquared.template.kotlin.src.qrcode.model.MyTicketing

class MyTicketingAdapter(private val itemList : ArrayList<MyTicketing>) :
    RecyclerView.Adapter<MyTicketingAdapter.ViewHolder>(){
    var datas = ArrayList<MyTicketing>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        /*
        @SerializedName("userIdx") val userIdx: Int,
        @SerializedName("movieName") val movieName: String,
        @SerializedName("theaterName") val theaterName: String,
        @SerializedName("startTime") val startTime: String,
        @SerializedName("count") var count: Int,
        @SerializedName("price") var price: Int,
        @SerializedName("idx") var idx: Int
        */

        var movieName: TextView = itemView.findViewById<TextView>(R.id.movieName)
        var theaterName: TextView = itemView.findViewById<TextView>(R.id.theaterName)
        var startTime: TextView = itemView.findViewById<TextView>(R.id.startTime)
        var count: TextView = itemView.findViewById<TextView>(R.id.count)
        var price: TextView = itemView.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_my_ticketing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.movieName.setText(itemList.get(position).movieName)
        holder.theaterName.setText(itemList.get(position).theaterName)
        holder.startTime.setText(itemList.get(position).startTime)
        holder.count.setText(itemList.get(position).count.toString()+"명")
        holder.price.setText(itemList.get(position).price.toString()+"명")
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}