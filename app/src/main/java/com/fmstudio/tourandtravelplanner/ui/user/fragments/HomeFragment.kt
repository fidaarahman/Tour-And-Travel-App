package com.fmstudio.tourandtravelplanner.ui.user.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.adapters.PopularDestinationAdapter
import com.fmstudio.tourandtravelplanner.databinding.FragmentHomeBinding
import com.fmstudio.tourandtravelplanner.model.PopularDestination
import com.fmstudio.tourandtravelplanner.ui.user.activities.HotelActivity
import com.fmstudio.tourandtravelplanner.ui.user.activities.PopularDestinationsActivity
import com.google.android.gms.common.util.CollectionUtils.listOf

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var layoutManager: LinearLayoutManager
    private var scrollHandler: Handler? = null
    private var scrollRunnable: Runnable? = null
    private var currentIndex = 0

    private val destinationList = listOf(
        PopularDestination("Skardu", R.drawable.ic_skardu, 4.8f),
        PopularDestination("Swat", R.drawable.iv_swat1, 3.6f),
        PopularDestination("ISB", R.drawable.isb, 2.9f)
        // Add more items if needed
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Click to Hotels
        binding.layoutHotels.setOnClickListener {
            startActivity(Intent(requireContext(), HotelActivity::class.java))
        }

        // Setup RecyclerView
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvPopularDestinations.layoutManager = layoutManager
        binding.rvPopularDestinations.adapter = PopularDestinationAdapter(destinationList)

        // Auto-scroll after every 2 seconds
        startAutoScroll()

        // See All click
        binding.tvSeeAll.setOnClickListener {
            Toast.makeText(requireContext(),"coming Soon in next!",Toast.LENGTH_SHORT).show()
           // startActivity(Intent(requireContext(), PopularDestinationsActivity::class.java))
        }
        binding.layoutTravel.setOnClickListener {
            Toast.makeText(requireContext(),"Coming Soon, Stay Connected!",Toast.LENGTH_SHORT).show()
        }
        binding.layoutTrip.setOnClickListener {
            Toast.makeText(requireContext(),"Coming Soon, Stay Connected!",Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }

    private fun startAutoScroll() {
        scrollHandler = Handler(Looper.getMainLooper())
        scrollRunnable = object : Runnable {
            override fun run() {
                if (destinationList.isNotEmpty()) {
                    currentIndex++
                    if (currentIndex >= destinationList.size) currentIndex = 0
                    binding.rvPopularDestinations.smoothScrollToPosition(currentIndex)
                    scrollHandler?.postDelayed(this, 2000) // 2 seconds delay
                }
            }
        }
        scrollHandler?.postDelayed(scrollRunnable!!, 2000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        stopAutoScroll()
    }

    private fun stopAutoScroll() {
        scrollHandler?.removeCallbacks(scrollRunnable!!)
        scrollHandler = null
        scrollRunnable = null
    }
}
