package com.fmstudio.tourandtravelplanner.ui.admin.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.adapters.TransportRequestAdapter
import com.fmstudio.tourandtravelplanner.databinding.ActivityShowTrasnportRequestBinding
import com.fmstudio.tourandtravelplanner.helper.HotelRequest
import com.fmstudio.tourandtravelplanner.helper.TransportRequest
import com.google.android.gms.common.util.CollectionUtils.listOf

class ShowTransportRequest : AppCompatActivity() {

    private lateinit var binding: ActivityShowTrasnportRequestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowTrasnportRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivBack.setOnClickListener {
            finish()
        }
        // Sample transport requests
        val transportList = listOf(
            TransportRequest("SkyLine Travels", "Skardu", "Pending", R.drawable.ic_bus1),
            TransportRequest("Mountain Movers", "Swat", "Pending", R.drawable.ic_bus2),
            TransportRequest("HighRoad Express", "Hunza", "Pending", R.drawable.ic_coaster)
        )

        // Setup RecyclerView with adapter
        binding.rvTransportRequests.layoutManager = LinearLayoutManager(this)
        val adapter = TransportRequestAdapter(transportList) { request ->
            // When "View Detail" is clicked, start TransportVerifactionActivity
            val intent = Intent(this, TransportVerifactionActivity::class.java)
            intent.putExtra("TRANSPORT_NAME", request.transportName) // Send transport name
            startActivity(intent)
        }
        binding.rvTransportRequests.adapter = adapter
    }
}
