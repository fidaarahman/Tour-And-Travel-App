package com.fmstudio.tourandtravelplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.HotelItemBinding
import com.fmstudio.tourandtravelplanner.helper.Hotel

class ShowHotelAdapter(
    private val hotels: List<Hotel>,
    private val onHotelClick: (Hotel) -> Unit
) : RecyclerView.Adapter<ShowHotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(val binding: HotelItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(hotel: Hotel) {
            binding.tvHotelName.text = hotel.name
            binding.ratingBar.rating = hotel.rating
            binding.tvPrice.text = hotel.price

            binding.root.setOnClickListener {
                onHotelClick(hotel)
            }
            binding.checkAvailability.setOnClickListener{
                onHotelClick(hotel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HotelItemBinding.inflate(inflater, parent, false)
        return HotelViewHolder(binding)
    }

    override fun getItemCount() = hotels.size

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bind(hotels[position])
    }
}

