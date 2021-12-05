package com.softsquared.template.kotlin.src.main.home.movie

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

class movieAdapter(private val itemList : ArrayList<movie>) :
    RecyclerView.Adapter<movieAdapter.ViewHolder>(){
    var datas = ArrayList<movie>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var category_img: ImageView = itemView.findViewById(R.id.img_categery)
        var category_txt: TextView = itemView.findViewById(R.id.txt_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_moive_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.getContext())
            .load(itemList.get(position).movieImg)
            .transform(CenterCrop(), RoundedCorners(50))
            .into(holder.category_img)
        holder.category_txt.setText(itemList.get(position).movieName)


        holder.itemView.setOnClickListener {
//            var intent = Intent(holder.itemView?.context, SearchCateogryActivity::class.java)
//            val name = itemList.get(position).category_name
//            intent.putExtra("category_name", name)
//            ContextCompat.startActivity(holder.itemView.context, intent, null)
            var intent = Intent(holder.itemView?.context, MovieDetailActivity::class.java)
            intent.putExtra("movieIdx", position)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}