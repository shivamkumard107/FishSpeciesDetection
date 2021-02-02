package com.myntra.android.speciesdetectionandroid.ui.catcharea

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.myntra.android.speciesdetectionandroid.R

class HeatmapRecycler(private val context: Context) :
    RecyclerView.Adapter<HeatmapRecycler.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fishImage = itemView.findViewById<ImageView>(R.id.ivProductImage)
        val fishName = itemView.findViewById<TextView>(R.id.tvCategory)
        val freq = itemView.findViewById<TextView>(R.id.tvfreq)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.heatmap_recycler, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }
}