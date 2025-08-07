package com.fmstudio.tourandtravelplanner.ui.user.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.databinding.ActivityPopularDestinationsBinding
import com.fmstudio.tourandtravelplanner.model.PopularDestination

import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.adapters.PopularDestinationAdapter
import com.google.android.gms.common.util.CollectionUtils.listOf

class PopularDestinationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPopularDestinationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopularDestinationsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val destinationList = listOf(
            PopularDestination("Paris", R.drawable.almas_hotel1, 4.8f),
            PopularDestination("Rome", R.drawable.ic_rosehotel1, 4.6f),
            PopularDestination("Tokyo", R.drawable.ic_pinehotel1, 4.9f),
//            PopularDestination("New York", R.drawable.newyork, 4.7f),
//            PopularDestination("Dubai", R.drawable.dubai, 4.5f)
        )

        binding.rvAllDestinations.layoutManager = LinearLayoutManager(this)
        binding.rvAllDestinations.adapter = PopularDestinationAdapter(destinationList)
    }
}
