package com.fmstudio.tourandtravelplanner.ui.manager.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.databinding.ActivityRoomsBinding
import com.fmstudio.tourandtravelplanner.ui.manager.adapters.RoomsAdapter
import com.fmstudio.tourandtravelplanner.ui.manager.data.RoomsViewModel

class RoomsActivity : AppCompatActivity() {

	private lateinit var binding: ActivityRoomsBinding
	private val viewModel: RoomsViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityRoomsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val adapter = RoomsAdapter(this, emptyList()) { room ->
			// TODO: approve logic via repository
		}
		binding.recyclerRooms.layoutManager = LinearLayoutManager(this)
		binding.recyclerRooms.adapter = adapter

		viewModel.rooms.observe(this) { list -> adapter.submitList(list) }
		viewModel.loadRooms()
	}
}