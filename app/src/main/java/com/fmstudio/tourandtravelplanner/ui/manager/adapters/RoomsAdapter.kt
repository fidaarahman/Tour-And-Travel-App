package com.fmstudio.tourandtravelplanner.ui.manager.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.ItemRoomBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.Room
import com.fmstudio.tourandtravelplanner.ui.manager.screens.AddRoomActivity

class RoomsAdapter(
	private val context: Context,
	private var rooms: List<Room>,
	private val onApprove: (Room) -> Unit
) : RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {

	class RoomViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
		val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return RoomViewHolder(binding)
	}

	override fun getItemCount(): Int = rooms.size

	override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
		val room = rooms[position]
		holder.binding.txtRoomNo.text = "Room No: ${room.roomNo}"
		holder.binding.txtBeds.text = "Beds: ${room.beds}"
		holder.binding.txtWashroom.text = "Washroom: ${if (room.washroom) "Yes" else "No"}"
		holder.binding.txtStatus.text = "Status: ${room.status}"

		if (room.status.equals("Pending", ignoreCase = true)) {
			holder.binding.btnApprove.visibility = View.VISIBLE
			holder.binding.btnAddRoom.visibility = View.GONE
			holder.binding.btnApprove.setOnClickListener { onApprove(room) }
		} else {
			holder.binding.btnApprove.visibility = View.GONE
			holder.binding.btnAddRoom.visibility = View.VISIBLE
			holder.binding.btnAddRoom.setOnClickListener {
				context.startActivity(Intent(context, AddRoomActivity::class.java))
			}
		}
	}

	fun submitList(newRooms: List<Room>) {
		this.rooms = newRooms
		notifyDataSetChanged()
	}
}