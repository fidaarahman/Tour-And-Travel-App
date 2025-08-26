package com.fmstudio.tourandtravelplanner.ui.manager.screens

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmstudio.tourandtravelplanner.databinding.ActivityAddRoomBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.ManagerRepository
import com.fmstudio.tourandtravelplanner.ui.manager.data.Room
import kotlinx.coroutines.launch

class AddRoomActivity : AppCompatActivity() {

	private lateinit var binding: ActivityAddRoomBinding
	private val repository = ManagerRepository()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAddRoomBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val statusOptions = listOf("Booked", "Pending", "Vacant")
		val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, statusOptions)
		binding.spinnerStatus.adapter = adapter

		binding.switchWashroom.setOnCheckedChangeListener { _, isChecked ->
			binding.txtWashroom.text = if (isChecked) "Yes" else "No"
		}

		binding.btnAdd.setOnClickListener {
			val roomNo = binding.etRoomNo.text.toString().trim()
			val beds = binding.etBeds.text.toString().trim().toIntOrNull() ?: 0
			val washroom = binding.switchWashroom.isChecked
			val status = binding.spinnerStatus.selectedItem?.toString() ?: "Pending"

			if (roomNo.isEmpty() || beds <= 0) {
				Toast.makeText(this, "Enter valid room and beds", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}

			lifecycleScope.launch {
				try {
					repository.addRoom(Room(roomNo = roomNo, beds = beds, washroom = washroom, status = status))
					Toast.makeText(this@AddRoomActivity, "Room added", Toast.LENGTH_SHORT).show()
					finish()
				} catch (e: Exception) {
					Toast.makeText(this@AddRoomActivity, e.message, Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
}