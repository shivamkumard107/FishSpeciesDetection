package com.myntra.android.speciesdetectionandroid.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myntra.android.speciesdetectionandroid.R
import kotlinx.android.synthetic.main.history_item.view.*

class CatchHistoryAdapter(private val context: Context) :
    RecyclerView.Adapter<CatchHistoryAdapter.ViewHolder>() {


    val namesList = arrayOf(
        "cantherhines_pardalis.jpg",
        "caranx_melampygus.jpg",
        "lutjanus_sebae.jpg"
        ,"cantherhines_pardalis_two.jpg"
        ,"caranx_melampygus_two.jpg"
        ,"pseudanthias_squamipinnis.jpg"
        ,"halichoeres_melanurus.jpg"
        ,"oxymonacanthus_longirostris.jpg"
        ,"oxymonacanthus_longirostris_two.jpg")

    val weigth = arrayOf(50,40,60,100,150,120,80,10,15,80,75)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.history_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val res = context.resources.getIdentifier(
            "com.myntra.android.speciesdetectionandroid:drawable/" + namesList.get(position).subSequence(0,namesList.get(position).length-4),
            null,
            null
        )

        holder.itemView.ivProductImage.setImageResource(res)
        holder.itemView.tvFishname.text = namesList.get(position).replace('_',' ').subSequence(0,namesList.get(position).length-4)
        holder.itemView.tvweight.text = "catch weight " + weigth.get(position) + " kg"
    }

    override fun getItemCount() = 9
}