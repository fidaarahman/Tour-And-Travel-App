package com.fmstudio.tourandtravelplanner.ui.user.activities
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.adapters.ShowHotelAdapter
import com.fmstudio.tourandtravelplanner.databinding.ActivityShowHotelsBinding
import com.fmstudio.tourandtravelplanner.helper.Hotel
import com.fmstudio.tourandtravelplanner.helper.SessionManager
import com.google.android.gms.common.util.CollectionUtils.listOf

class ShowHotelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowHotelsBinding
    private lateinit var hotelAdapter: ShowHotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowHotelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Load city from SessionManager
        val cityName = SessionManager.getString("key_selected_city", "your city")
        binding.tvCityTitle.text = "Hotels in $cityName"

        // Sample Data
        val hotels = listOf(
            Hotel("Almas Hotel", 3f, "PKR 4500"),
            Hotel("Pearl Continental", 5f, "PKR 12000"),
            Hotel("Hotel One", 4f, "PKR 7500")
        )

        // Set up adapter with click callback
        hotelAdapter = ShowHotelAdapter(hotels) { selectedHotel ->
            val intent = Intent(this, ShowHotelDetailActivity::class.java).apply {
                putExtra("hotel_name", selectedHotel.name)
                putExtra("hotel_rating", selectedHotel.rating)
                putExtra("hotel_price", selectedHotel.price)
            }
            startActivity(intent)
        }

        binding.recyclerViewHotels.apply {
            layoutManager = LinearLayoutManager(this@ShowHotelsActivity)
            adapter = hotelAdapter
        }
    }
}

