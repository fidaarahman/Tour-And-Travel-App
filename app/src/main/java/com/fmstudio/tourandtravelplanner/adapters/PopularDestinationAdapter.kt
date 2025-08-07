package com.fmstudio.tourandtravelplanner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.model.PopularDestination
import com.google.android.gms.common.util.CollectionUtils.listOf

class PopularDestinationAdapter(
    private val list: List<PopularDestination>
) : RecyclerView.Adapter<PopularDestinationAdapter.PopularViewHolder>() {

    inner class PopularViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPlace: ImageView = view.findViewById(R.id.img_place)
        val tvName: TextView = view.findViewById(R.id.tv_place_name)
        val stars = listOf(
            view.findViewById<ImageView>(R.id.star1),
            view.findViewById<ImageView>(R.id.star2),
            view.findViewById<ImageView>(R.id.star3),
            view.findViewById<ImageView>(R.id.star4),
            view.findViewById<ImageView>(R.id.star5)
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_popular_destination, parent, false)
        return PopularViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val destination = list[position]
        holder.imgPlace.setImageResource(destination.imageResId)
        holder.tvName.text = destination.name

        val rating = destination.rating
        for (i in 0 until 5) {
            val starIndex = i + 1
            val resId = when {
                rating >= starIndex -> R.drawable.ic_full_star
                rating >= starIndex - 0.5 -> R.drawable.ic_half_star
                else -> R.drawable.ic_star_empty
            }
            holder.stars[i].setImageResource(resId)
        }
    }

    override fun getItemCount(): Int = list.size
}
