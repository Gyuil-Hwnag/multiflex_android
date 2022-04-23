package com.softsquared.template.kotlin.src.ticketing

import android.app.Activity
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.ticketing.model.CurrentSeat
import com.softsquared.template.kotlin.src.ticketing.model.Seat

class SeatAdapter(private val itemList : ArrayList<CurrentSeat>, var Activity: TicketingActivity, val userIdx: Int) :
    RecyclerView.Adapter<SeatAdapter.ViewHolder>(){
    var datas = ArrayList<CurrentSeat>()
    var count = 0

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView){
        var seat_num: TextView = itemView.findViewById<TextView>(R.id.seat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_seat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var count_txt = Activity.findViewById<TextView>(R.id.count)
        var price = Activity.findViewById<TextView>(R.id.price)

        if(itemList.get(position).userIdx == 0){
            itemList.get(position).is_check = false
            holder.seat_num.setText(itemList.get(position).seatIdx.toString())
            holder.seat_num.setBackgroundResource(R.drawable.seat_select_custom)
            holder.seat_num.setTextColor(Color.parseColor("#000000"))
        }
        else{
            itemList.get(position).is_check = true
            holder.seat_num.setText(itemList.get(position).seatIdx.toString())
            holder.seat_num.setTextColor(Color.parseColor("#FFFFFF"))
            holder.seat_num.setBackgroundResource(R.drawable.seat_select_ok_custom)
        }

        holder.itemView.setOnClickListener {
            if(itemList.get(position).is_check && itemList.get(position).userIdx == 0){
                count = count-1
                count_txt.setText(count.toString()+" 명")
                price.setText((count*10000).toString()+" 원")
                itemList.get(position).is_check = false
                holder.seat_num.setBackgroundResource(R.drawable.seat_select_custom)
                holder.seat_num.setTextColor(Color.parseColor("#000000"))
            }
            else if((!itemList.get(position).is_check) && itemList.get(position).userIdx == 0){
                itemList.get(position).is_check = true
                count = count+1
                count_txt.setText(count.toString()+" 명")
                price.setText((count*10000).toString()+" 원")
                holder.seat_num.setTextColor(Color.parseColor("#FFFFFF"))
                holder.seat_num.setBackgroundResource(R.drawable.seat_select_ok_custom)
            }
        }

    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}