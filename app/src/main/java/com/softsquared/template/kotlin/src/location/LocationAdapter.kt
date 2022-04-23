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
        var branchName: TextView = itemView.findViewById<TextView>(R.id.branchName)
        var branchAddress: TextView = itemView.findViewById<TextView>(R.id.branchAddress)
        var branchTelecom: TextView = itemView.findViewById<TextView>(R.id.branchTelecom)
        var locationName: TextView = itemView.findViewById<TextView>(R.id.locationName)
        var txt1: TextView = itemView.findViewById<TextView>(R.id.txt1)
        var txt2: TextView = itemView.findViewById<TextView>(R.id.txt2)
        var txt3: TextView = itemView.findViewById<TextView>(R.id.txt3)
        var txt4: TextView = itemView.findViewById<TextView>(R.id.txt4)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_location, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!itemList.get(position).is_check){
            holder.itemView.setBackgroundResource(R.drawable.login_edit_shape)
            holder.txt1.setTextColor(Color.parseColor("#000000"))
            holder.txt2.setTextColor(Color.parseColor("#000000"))
            holder.txt3.setTextColor(Color.parseColor("#000000"))
            holder.txt4.setTextColor(Color.parseColor("#000000"))

            holder.branchName.setText(itemList.get(position).branchName)
            holder.branchName.setTextColor(Color.parseColor("#000000"))

            holder.branchAddress.setText(itemList.get(position).branchAddress)
            holder.branchAddress.setTextColor(Color.parseColor("#000000"))

            holder.branchTelecom.setText(itemList.get(position).branchTelecom)
            holder.branchTelecom.setTextColor(Color.parseColor("#000000"))

            holder.locationName.setText(itemList.get(position).locationName)
            holder.locationName.setTextColor(Color.parseColor("#000000"))
        }
        else{
            count = count+1
            holder.txt1.setTextColor(Color.parseColor("#FFFFFF"))
            holder.txt2.setTextColor(Color.parseColor("#FFFFFF"))
            holder.txt3.setTextColor(Color.parseColor("#FFFFFF"))
            holder.txt4.setTextColor(Color.parseColor("#FFFFFF"))
            holder.itemView.setBackgroundResource(R.drawable.login_btn_shape)

            holder.branchName.setText(itemList.get(position).branchName)
            holder.branchName.setTextColor(Color.parseColor("#FFFFFF"))

            holder.branchAddress.setText(itemList.get(position).branchAddress)
            holder.branchAddress.setTextColor(Color.parseColor("#FFFFFF"))

            holder.branchTelecom.setText(itemList.get(position).branchTelecom)
            holder.branchTelecom.setTextColor(Color.parseColor("#FFFFFF"))

            holder.locationName.setText(itemList.get(position).locationName)
            holder.locationName.setTextColor(Color.parseColor("#FFFFFF"))
        }

        holder.itemView.setOnClickListener {
            if(count == 0){
                if(itemList.get(position).is_check){
                    count = count-1
                    holder.txt1.setTextColor(Color.parseColor("#000000"))
                    holder.txt2.setTextColor(Color.parseColor("#000000"))
                    holder.txt3.setTextColor(Color.parseColor("#000000"))
                    holder.txt4.setTextColor(Color.parseColor("#000000"))
                    holder.itemView.setBackgroundResource(R.drawable.login_edit_shape)

                    itemList.get(position).is_check = false
                    holder.branchName.setText(itemList.get(position).branchName)
                    holder.branchName.setTextColor(Color.parseColor("#000000"))

                    holder.branchAddress.setText(itemList.get(position).branchAddress)
                    holder.branchAddress.setTextColor(Color.parseColor("#000000"))

                    holder.branchTelecom.setText(itemList.get(position).branchTelecom)
                    holder.branchTelecom.setTextColor(Color.parseColor("#000000"))

                    holder.locationName.setText(itemList.get(position).locationName)
                    holder.locationName.setTextColor(Color.parseColor("#000000"))
                }
                else{
                    count = count+1
                    itemList.get(position).is_check = true
                    holder.itemView.setBackgroundResource(R.color.register_login_blue)
                    holder.txt1.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.txt2.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.txt3.setTextColor(Color.parseColor("#FFFFFF"))
                    holder.txt4.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.branchName.setText(itemList.get(position).branchName)
                    holder.branchName.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.branchAddress.setText(itemList.get(position).branchAddress)
                    holder.branchAddress.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.branchTelecom.setText(itemList.get(position).branchTelecom)
                    holder.branchTelecom.setTextColor(Color.parseColor("#FFFFFF"))

                    holder.locationName.setText(itemList.get(position).locationName)
                    holder.locationName.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}