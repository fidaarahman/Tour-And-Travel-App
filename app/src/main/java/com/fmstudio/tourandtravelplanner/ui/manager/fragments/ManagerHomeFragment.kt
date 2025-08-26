package com.fmstudio.tourandtravelplanner.ui.manager.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmstudio.tourandtravelplanner.databinding.FragmentManagerHomeBinding
import com.fmstudio.tourandtravelplanner.ui.manager.screens.AddRoomActivity
import com.fmstudio.tourandtravelplanner.ui.manager.screens.RoomsActivity

class ManagerHomeFragment : Fragment() {

	private var _binding: FragmentManagerHomeBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentManagerHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnAddRooms.setOnClickListener {
			startActivity(Intent(requireContext(), AddRoomActivity::class.java))
		}

		binding.btnViewRooms.setOnClickListener {
			startActivity(Intent(requireContext(), RoomsActivity::class.java))
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}