package com.fmstudio.tourandtravelplanner.ui.admin.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fmstudio.tourandtravelplanner.databinding.FragmentAdminHomeBinding
import com.fmstudio.tourandtravelplanner.ui.admin.activities.ShowHotelRequest

import com.fmstudio.tourandtravelplanner.ui.admin.activities.ShowTransportRequest

class AdminHomeFragment : Fragment() {

    private lateinit var binding: FragmentAdminHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)

        binding.tvHotelrequest.setOnClickListener {
            val intent = Intent(requireContext(), ShowHotelRequest::class.java)
            startActivity(intent)
        }

        binding.tvTransportrequest.setOnClickListener {
            val intent = Intent(requireContext(), ShowTransportRequest::class.java)
            startActivity(intent)
        }
        return binding.root
    }

}
