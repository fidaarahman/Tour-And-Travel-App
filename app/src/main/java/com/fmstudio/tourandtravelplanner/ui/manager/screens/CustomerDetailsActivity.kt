package com.fmstudio.tourandtravelplanner.ui.manager.screens

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fmstudio.tourandtravelplanner.databinding.ActivityCustomerDetailsBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.ManagerRepository
import kotlinx.coroutines.launch

class CustomerDetailsActivity : AppCompatActivity() {

	private lateinit var binding: ActivityCustomerDetailsBinding
	private val repository = ManagerRepository()
	private var customerId: String? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityCustomerDetailsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		customerId = intent.getStringExtra("customerId")

		lifecycleScope.launch {
			try {
				val customer = repository.getCustomer(customerId ?: return@launch)
				if (customer != null) {
					binding.txtName.text = customer.name
					binding.txtEmail.text = customer.email
					binding.txtPhone.text = customer.phone
					binding.txtCnic.text = customer.cnic
					binding.txtRoomNo.text = customer.roomNo
					binding.txtBeds.text = customer.beds.toString()
					binding.txtWashroom.text = if (customer.washroom) "Yes" else "No"
					binding.txtPersons.text = customer.persons.toString()
				}
			} catch (e: Exception) {
				Toast.makeText(this@CustomerDetailsActivity, e.message, Toast.LENGTH_SHORT).show()
			}
		}

		binding.btnApprove.setOnClickListener {
			lifecycleScope.launch {
				try {
					repository.approveCustomer(customerId ?: return@launch)
					Toast.makeText(this@CustomerDetailsActivity, "Approved", Toast.LENGTH_SHORT).show()
					finish()
				} catch (e: Exception) {
					Toast.makeText(this@CustomerDetailsActivity, e.message, Toast.LENGTH_SHORT).show()
				}
			}
		}
	}
}