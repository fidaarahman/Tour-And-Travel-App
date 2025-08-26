package com.fmstudio.tourandtravelplanner.ui.manager.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fmstudio.tourandtravelplanner.databinding.FragmentManagerProfileBinding
import com.fmstudio.tourandtravelplanner.ui.manager.data.Manager
import com.fmstudio.tourandtravelplanner.ui.manager.data.ManagerProfileViewModel
import com.fmstudio.tourandtravelplanner.ui.manager.screens.CustomersActivity
import android.content.Intent

class ManagerProfileFragment : Fragment() {

	private var _binding: FragmentManagerProfileBinding? = null
	private val binding get() = _binding!!
	private val viewModel: ManagerProfileViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentManagerProfileBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewModel.manager.observe(viewLifecycleOwner) { mgr ->
			binding.txtName.text = mgr?.name ?: "-"
			binding.txtEmail.text = mgr?.email ?: "-"
			binding.txtPhone.text = mgr?.phone ?: "-"
			binding.txtCnic.text = mgr?.cnic ?: "-"
		}
		viewModel.loadProfile()

		binding.btnEditProfile.setOnClickListener { showEditDialog() }
		binding.btnCustomers.setOnClickListener {
			startActivity(Intent(requireContext(), CustomersActivity::class.java))
		}
		binding.btnSettings.setOnClickListener {
			Toast.makeText(requireContext(), "Settings coming soon", Toast.LENGTH_SHORT).show()
		}
	}

	private fun showEditDialog() {
		val ctx = requireContext()
		val dialogView = LayoutInflater.from(ctx).inflate(android.R.layout.simple_list_item_2, null)
		val nameInput = EditText(ctx); nameInput.hint = "Name"
		val phoneInput = EditText(ctx); phoneInput.hint = "Phone"
		val cnicInput = EditText(ctx); cnicInput.hint = "CNIC"

		val container = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
		val layout = android.widget.LinearLayout(ctx).apply {
			orientation = android.widget.LinearLayout.VERTICAL
			setPadding(32, 32, 32, 0)
			addView(nameInput, container)
			addView(phoneInput, container)
			addView(cnicInput, container)
		}

		AlertDialog.Builder(ctx)
			.setTitle("Edit Profile")
			.setView(layout)
			.setPositiveButton("Save") { d, _ ->
				val current = viewModel.manager.value
				val updated = Manager(
					id = current?.id ?: "",
					name = nameInput.text.toString().ifEmpty { current?.name ?: "" },
					email = current?.email ?: "",
					phone = phoneInput.text.toString().ifEmpty { current?.phone ?: "" },
					cnic = cnicInput.text.toString().ifEmpty { current?.cnic ?: "" }
				)
				viewModel.saveProfile(updated)
				Toast.makeText(ctx, "Profile updated", Toast.LENGTH_SHORT).show()
				d.dismiss()
			}
			.setNegativeButton("Cancel") { d, _ -> d.dismiss() }
			.show()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}