package com.softsquared.template.kotlin.src.movie_select

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.movie_detail.MovieDetailActivity
import com.softsquared.template.kotlin.src.ticketing.TicketingActivity

class movieselectAdapter(private val itemList : ArrayList<movieselect>) :
    RecyclerView.Adapter<movieselectAdapter.ViewHolder>(){
    var datas = ArrayList<movieselect>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var category_img: ImageView = itemView.findViewById(R.id.img_categery)
        var category_txt: TextView = itemView.findViewById(R.id.txt_category)
        var movie_summary: TextView = itemView.findViewById(R.id.movie_summary)
        var movie_star: TextView = itemView.findViewById(R.id.movie_star)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_moive_select, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.getContext())
            .load(itemList.get(position).movieImg)
            .transform(CenterCrop(), RoundedCorners(50))
            .into(holder.category_img)
        holder.category_txt.setText(itemList.get(position).movieName)
        holder.movie_summary.setText(itemList.get(position).movieSummary)
        holder.movie_star.setText(itemList.get(position).movieStar.toString())

        holder.itemView.setOnClickListener {
            var movieIdx = position+1
            var intent = Intent(holder.itemView?.context, TicketingActivity::class.java)
            intent.putExtra("movieIdx", movieIdx)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}