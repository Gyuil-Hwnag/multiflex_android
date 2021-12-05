package com.softsquared.template.kotlin.src.movieSearch

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.home.movie.movie
import com.softsquared.template.kotlin.src.movieSearch.model.MovieDto
import com.softsquared.template.kotlin.src.movie_detail.MovieDetailActivity

class MovieSearchAdapter(private val itemList : ArrayList<MovieDto>) :
    RecyclerView.Adapter<MovieSearchAdapter.ViewHolder>(){
    var datas = ArrayList<MovieDto>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var movie_rank: TextView = itemView.findViewById(R.id.rank)
        var movie_name: TextView = itemView.findViewById(R.id.movie_name)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_boxoffice_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.movie_rank.setText(itemList.get(position).rank)
        holder.movie_name.setText(itemList.get(position).movieNm)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}