package com.fmstudio.tourandtravelplanner.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fmstudio.tourandtravelplanner.databinding.HotelImageItemBinding

class HotelImagesAdapter(
    private val context: Context,
    private val imageList: List<String>,
    private val onImageClick: (Int) -> Unit
) : RecyclerView.Adapter<HotelImagesAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: HotelImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = if (imageList.size > 6) 6 else imageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HotelImageItemBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val isLast = position == 5 && imageList.size > 6
        val imageUrl = imageList[position]

        holder.binding.apply {
            Glide.with(context)
                .load(imageUrl)
                .centerCrop()
                .into(ivHotelPic)

            tvMorePhotos.visibility = if (isLast) View.VISIBLE else View.GONE
            ivOverlay.visibility = if (isLast) View.VISIBLE else View.GONE

            if (isLast) {
                tvMorePhotos.text = "+${imageList.size - 5} photos"
            }

            root.setOnClickListener {
                onImageClick(position)
            }
        }
    }
}
