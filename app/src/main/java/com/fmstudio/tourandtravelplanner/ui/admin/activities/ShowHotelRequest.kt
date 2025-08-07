package com.fmstudio.tourandtravelplanner.ui.admin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.adapters.HotelRequestAdapter
import com.fmstudio.tourandtravelplanner.databinding.ActivityShowHotelRequestBinding
import com.fmstudio.tourandtravelplanner.helper.HotelRequest
import com.google.android.gms.common.util.CollectionUtils.listOf

class ShowHotelRequest : AppCompatActivity() {

    private lateinit var binding: ActivityShowHotelRequestBinding
    private lateinit var hotelAdapter: HotelRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowHotelRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener {
            finish()
        }
        val hotelRequests = listOf(
            HotelRequest("Almas hotel", "Swat", "Pending", R.drawable.ic_almashotel),
            HotelRequest("Rose Palace", "Murree", "Pending", R.drawable.ic_rosehotel1),
            HotelRequest("Pine View", "Hunza", "Pending", R.drawable.ic_pinehotel1)
        )

        hotelAdapter = HotelRequestAdapter(hotelRequests)
        binding.rvhotelRequests.apply {
            layoutManager = LinearLayoutManager(this@ShowHotelRequest)
            adapter = hotelAdapter
        }
    }
}
