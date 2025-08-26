package com.fmstudio.tourandtravelplanner.ui.manager.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.databinding.ItemRoomBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.Customer
import com.fmstudio.tourandtravelplanner.ui.manager.screens.CustomerDetailsActivity

class CustomersAdapter(
	private val context: Context,
	private var customers: List<Customer>
) : RecyclerView.Adapter<CustomersAdapter.CustomerViewHolder>() {

	class CustomerViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
		val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return CustomerViewHolder(binding)
	}

	override fun getItemCount(): Int = customers.size

	override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
		val c = customers[position]
		holder.binding.txtRoomNo.text = "${c.name} (${c.persons} persons)"
		holder.binding.txtBeds.text = "Email: ${c.email}"
		holder.binding.txtWashroom.text = "Phone: ${c.phone}"
		holder.binding.txtStatus.text = "Room: ${c.roomNo}"
		holder.binding.btnApprove.text = "Details"
		holder.binding.btnAddRoom.text = "Approve"
		holder.binding.btnApprove.setOnClickListener {
			val intent = Intent(context, CustomerDetailsActivity::class.java)
			intent.putExtra("customerId", c.id)
			context.startActivity(intent)
		}
		holder.binding.btnAddRoom.setOnClickListener {
			val intent = Intent(context, CustomerDetailsActivity::class.java)
			intent.putExtra("customerId", c.id)
			context.startActivity(intent)
		}
	}

	fun submitList(newList: List<Customer>) { customers = newList; notifyDataSetChanged() }
}