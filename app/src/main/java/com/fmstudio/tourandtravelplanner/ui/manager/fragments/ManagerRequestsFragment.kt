package com.fmstudio.tourandtravelplanner.ui.manager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.databinding.FragmentManagerRequestsBinding
import com.fmstudio.tourandtravelplanner.ui.manager.adapters.RequestsAdapter
import com.fmstudio.tourandtravelplanner.ui.manager.data.RequestsViewModel

class ManagerRequestsFragment : Fragment() {

	private var _binding: FragmentManagerRequestsBinding? = null
	private val binding get() = _binding!!
	private val viewModel: RequestsViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentManagerRequestsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val adapter = RequestsAdapter(requireContext(), emptyList()) { request ->
			viewModel.approve(request.id)
		}
		binding.recyclerRequests.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerRequests.adapter = adapter

		viewModel.requests.observe(viewLifecycleOwner) { list -> adapter.submitList(list) }
		viewModel.loadRequests()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}