package com.softsquared.template.kotlin.src.movie_time

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

class MovietimeAdapter(private val itemList : ArrayList<MovieTime>) :
    RecyclerView.Adapter<MovietimeAdapter.ViewHolder>(){
    var datas = ArrayList<MovieTime>()
    var count = 0

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        /*
        @SerializedName("branchName") val branchName: String,
        @SerializedName("branchAddress") val branchAddress: String,
        @SerializedName("branchTelecom") val branchTelecom: String,
        @SerializedName("locationIdx") val locationIdx: Int,
        @SerializedName("locationName") var locationName: String,
        @SerializedName("idx") var idx: Int
        */
        var movie_date: TextView = itemView.findViewById<TextView>(R.id.movie_date)
        var movie_time: TextView = itemView.findViewById<TextView>(R.id.movie_time)
        var txt1: TextView = itemView.findViewById<TextView>(R.id.txt1)
        var txt2: TextView = itemView.findViewById<TextView>(R.id.txt2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_movie_time, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!itemList.get(position).is_check){
            holder.itemView.setBackgroundResource(R.drawable.login_edit_shape)
            holder.txt1.setTextColor(Color.parseColor("#000000"))
            holder.txt2.setTextColor(Color.parseColor("#000000"))

            holder.movie_date.setText(itemList.get(position).showdate)
            holder.movie_date.setTextColor(Color.parseColor("#000000"))

            holder.movie_time.setText(itemList.get(position).startTime+" ~ "+itemList.get(position).endTime)
            holder.movie_time.setTextColor(Color.parseColor("#000000"))
        }
        else{
            count = count+1
            holder.txt1.setTextColor(Color.parseColor("#FFFFFF"))
            holder.txt2.setTextColor(Color.parseColor("#FFFFFF"))
            holder.itemView.setBackgroundResource(R.drawable.login_btn_shape)

            holder.movie_date.setText(itemList.get(position).showdate)
            holder.movie_date.setTextColor(Color.parseColor("#FFFFFF"))

            holder.movie_time.setText(itemList.get(position).startTime+" ~ "+itemList.get(position).endTime)
            holder.movie_time.setTextColor(Color.parseColor("#FFFFFF"))

        }

        holder.itemView.setOnClickListener {
            if(count == 0){
                if(itemList.get(position).is_check){
                    count = count-1
                    holder.txt1.setTextColor(Color.parseColor("#000000"))
                    holder.txt2.setTextColor(Color.parseColor("#000000"))
                    holder.itemView.setBackgroundResource(R.drawable.login_edit_shape)

                    itemList.get(position).is_check = false
                    holder.movie_date.setText(itemList.get(position).showdate)
                    holder.movie_date.setTextColor(Color.parseColor("#000000"))

                    holder.movie_time.setText(itemList.get(position).startTime+" ~ "+itemList.get(position).endTime)
                    holder.movie_time.setTextColor(Color.parseColor("#000000"))
                }
                else{
                    count = count+1
                    itemList.get(position).is_check = true
                    holder.itemView.setBackgroundResource(R.drawable.login_btn_shape)
                    holder.txt1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.txt2.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.movie_date.setText(itemList.get(position).showdate)
                    holder.movie_date.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.movie_time.setText(itemList.get(position).startTime+" ~ "+itemList.get(position).endTime.toString())
                    holder.movie_time.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}