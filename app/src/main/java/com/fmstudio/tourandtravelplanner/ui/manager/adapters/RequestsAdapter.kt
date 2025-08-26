package com.fmstudio.tourandtravelplanner.ui.manager.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.ItemRequestBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.RequestDisplay
import com.fmstudio.tourandtravelplanner.ui.manager.screens.AddRoomActivity

class RequestsAdapter(
	private val context: Context,
	private var requests: List<RequestDisplay>,
	private val onApprove: (RequestDisplay) -> Unit
) : RecyclerView.Adapter<RequestsAdapter.RequestViewHolder>() {

	class RequestViewHolder(val binding: ItemRequestBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
		val binding = ItemRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return RequestViewHolder(binding)
	}

	override fun getItemCount(): Int = requests.size

	override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
		val req = requests[position]
		holder.binding.txtReqRoomNo.text = "Room No: ${req.roomNo}"
		holder.binding.txtReqBeds.text = "Beds: ${req.beds}"
		holder.binding.txtReqWashroom.text = "Washroom: ${if (req.washroom) "Yes" else "No"}"
		holder.binding.txtReqStatus.text = "Status: ${req.status}"

		if (req.status.equals("Pending", ignoreCase = true)) {
			holder.binding.btnApprove.visibility = View.VISIBLE
			holder.binding.btnAddRoom.visibility = View.GONE
			holder.binding.btnApprove.setOnClickListener { onApprove(req) }
		} else {
			holder.binding.btnApprove.visibility = View.GONE
			holder.binding.btnAddRoom.visibility = View.VISIBLE
			holder.binding.btnAddRoom.setOnClickListener {
				context.startActivity(Intent(context, AddRoomActivity::class.java))
			}
		}
	}

	fun submitList(newList: List<RequestDisplay>) {
		this.requests = newList
		notifyDataSetChanged()
	}
}