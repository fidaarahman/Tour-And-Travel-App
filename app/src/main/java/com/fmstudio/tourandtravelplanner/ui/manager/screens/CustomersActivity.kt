package com.fmstudio.tourandtravelplanner.ui.manager.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.databinding.ActivityCustomersBinding
import com.fmstudio.tourandtravelplanner.ui.manager.adapters.CustomersAdapter
import com.fmstudio.tourandtravelplanner.ui.manager.data.CustomersViewModel

class CustomersActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCustomersBinding
	private val viewModel: CustomersViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCustomersBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val adapter = CustomersAdapter(this, emptyList())
		binding.recyclerCustomers.layoutManager = LinearLayoutManager(this)
		binding.recyclerCustomers.adapter = adapter

		viewModel.customers.observe(this) { adapter.submitList(it) }
		viewModel.loadCustomers()
	}
}