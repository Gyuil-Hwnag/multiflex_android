package com.softsquared.template.kotlin.src.ticketing

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.ticketing.model.Seat

class SeatAdapter(private val itemList : ArrayList<Seat>) :
    RecyclerView.Adapter<SeatAdapter.ViewHolder>(){
    var datas = ArrayList<Seat>()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var seat_num: TextView = itemView.findViewById<TextView>(R.id.seat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_seat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!itemList.get(position).is_check){
            holder.seat_num.setText(itemList.get(position).seat_num.toString())
            holder.seat_num.setBackgroundResource(R.drawable.seat_select_custom)
            holder.seat_num.setTextColor(Color.parseColor("#000000"))
        }
        else{
            holder.seat_num.setTextColor(Color.parseColor("#FFFFFF"))
            holder.seat_num.setBackgroundResource(R.drawable.seat_select_ok_custom)
        }

        holder.itemView.setOnClickListener {
            if(itemList.get(position).is_check){
                itemList.get(position).is_check = false
                holder.seat_num.setBackgroundResource(R.drawable.seat_select_custom)
                holder.seat_num.setTextColor(Color.parseColor("#000000"))
            }
            else{
                itemList.get(position).is_check = true
                holder.seat_num.setTextColor(Color.parseColor("#FFFFFF"))
                holder.seat_num.setBackgroundResource(R.drawable.seat_select_ok_custom)
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}