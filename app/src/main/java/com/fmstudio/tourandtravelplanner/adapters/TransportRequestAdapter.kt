package com.fmstudio.tourandtravelplanner.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.ItemTransportRequestBinding
import com.fmstudio.tourandtravelplanner.helper.HotelRequest
import com.fmstudio.tourandtravelplanner.helper.TransportRequest

class TransportRequestAdapter(
    private val transportList: List<TransportRequest>,
    private val onItemClick: (TransportRequest) -> Unit // Click listener
) : RecyclerView.Adapter<TransportRequestAdapter.TransportViewHolder>() {

    inner class TransportViewHolder(val binding: ItemTransportRequestBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransportViewHolder {
        val binding = ItemTransportRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransportViewHolder, position: Int) {
        val transport = transportList[position]
        holder.binding.tvTransportName.text = "Company Name : ${transport.transportName}"
        holder.binding.tvLocation.text = "Location : ${transport.location}"
        holder.binding.tvStatus.text = "Status : ${transport.status}"
        holder.binding.ivTransport.setImageResource(transport.imageResId)

        holder.binding.btnViewDetail.setOnClickListener {
            onItemClick(transport)
        }
    }

    override fun getItemCount(): Int = transportList.size
}
