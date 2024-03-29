package com.myntra.android.speciesdetectionandroid.ui.notif

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.notif_item.view.*

class NotificationAdapter(
    private val context: Context,
    private val list: ArrayList<NotificationModel>
) :
    RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val desc = itemView.findViewById<TextView>(R.id.tvDescription)
        val timestamp = itemView.findViewById<TextView>(R.id.tvTimeStamp)
        val notifType = itemView.findViewById<ImageView>(R.id.notif_type)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.notif_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tvTitle.text = list[position].title
        holder.itemView.tvDescription.text = list[position].desc
        holder.itemView.tvTimeStamp.text = list[position].timestamp
        when(list[position].notifType){
            "warning" -> Glide.with(context).load(R.drawable.warning).into(holder.itemView.notif_type)
            "alert" -> Glide.with(context).load(R.drawable.alert).into(holder.itemView.notif_type)
            "good" -> Glide.with(context).load(R.drawable.heart).into(holder.itemView.notif_type)
        }
    }

    override fun getItemCount(): Int {
        return list.size;
    }

}