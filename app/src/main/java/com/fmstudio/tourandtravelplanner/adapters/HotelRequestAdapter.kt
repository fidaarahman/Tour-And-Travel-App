package com.fmstudio.tourandtravelplanner.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ItemHotelRequestBinding
import com.fmstudio.tourandtravelplanner.helper.HotelRequest
import com.fmstudio.tourandtravelplanner.ui.admin.activities.HotelVerificationActivity

class HotelRequestAdapter(
    private val hotelList: List<HotelRequest>
) : RecyclerView.Adapter<HotelRequestAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(val binding: ItemHotelRequestBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val binding = ItemHotelRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hotelList[position]
        holder.binding.hotelName.text = "Hotel Name : ${hotel.hotelName}"
        holder.binding.location.text = "Location : ${hotel.location}"
        holder.binding.status.text = "Status : ${hotel.status}"
        holder.binding.imageView.setImageResource(hotel.imageResId)

        holder.binding.tvViewdetail.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, HotelVerificationActivity::class.java)
            intent.putExtra("HOTEL_NAME", hotel.hotelName)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = hotelList.size
}
