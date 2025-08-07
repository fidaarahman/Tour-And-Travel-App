package com.fmstudio.tourandtravelplanner.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.ItemCityBinding

class DisplayCityAdapter(
    private val cities: List<String>,
    private val onCityClick: (String) -> Unit
) : RecyclerView.Adapter<DisplayCityAdapter.CityViewHolder>() {

    inner class CityViewHolder(private val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(city: String) {
            binding.cityName.text = city
            binding.root.setOnClickListener {
                onCityClick(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount() = cities.size
}
