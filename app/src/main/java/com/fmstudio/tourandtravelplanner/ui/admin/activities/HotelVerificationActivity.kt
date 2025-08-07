package com.fmstudio.tourandtravelplanner.ui.admin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityHotelVerificationBinding


class HotelVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotelVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get hotel name from intent
        val hotelName = intent.getStringExtra("HOTEL_NAME") ?: "No Hotel Name"
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvHotelName.text = hotelName

        binding.tvHotelName.text = hotelName

        when (hotelName) {
            "Almas hotel" -> {
                binding.ivHotel.setImageResource(R.drawable.ic_almashotel)
                binding.tvOwner.text = "Owner: Mr. Ali Khan"
                binding.tvLocation.text = "Location: Swat, Pakistan"
                binding.tvAddress.text = "Address: Mingora Bypass Road"
                binding.tvEmail.text = "Email: ali.khan@example.com"
                binding.tvPhone.text = "Phone: +92 300 1234567"
                binding.tvAboutDescription.text = "Almas Hotel is known for its hospitality in the scenic Swat valley."
                binding.tvTotalRooms.text = "Total Rooms: 50"
                binding.tvDeluxeRooms.text = "Deluxe Rooms: 30"
                binding.tvSuites.text = "Suites: 20"
                binding.tvPlatformName.text = "Booking.com"
            }

            "Rose Palace" -> {
                binding.ivHotel.setImageResource(R.drawable.ic_rosehotel1)
                binding.tvOwner.text = "Owner: Mrs. Sara Ahmed"
                binding.tvLocation.text = "Location: Murree, Pakistan"
                binding.tvAddress.text = "Address: Mall Road, Near Patriata"
                binding.tvEmail.text = "Email: sara.ahmed@example.com"
                binding.tvPhone.text = "Phone: +92 345 9876543"
                binding.tvAboutDescription.text = "Rose Palace offers a peaceful mountain getaway with luxury rooms."
                binding.tvTotalRooms.text = "Total Rooms: 40"
                binding.tvDeluxeRooms.text = "Deluxe Rooms: 25"
                binding.tvSuites.text = "Suites: 15"
                binding.tvPlatformName.text = "Agoda"
            }

            "Pine View" -> {
                binding.ivHotel.setImageResource(R.drawable.ic_pinehotel1)
                binding.tvOwner.text = "Owner: Mr. Imran Baig"
                binding.tvLocation.text = "Location: Hunza, Pakistan"
                binding.tvAddress.text = "Address: Karimabad, Hunza"
                binding.tvEmail.text = "Email: imran.baig@example.com"
                binding.tvPhone.text = "Phone: +92 312 1122334"
                binding.tvAboutDescription.text = "Pine View is a scenic resort offering breathtaking views of Hunza valley."
                binding.tvTotalRooms.text = "Total Rooms: 35"
                binding.tvDeluxeRooms.text = "Deluxe Rooms: 20"
                binding.tvSuites.text = "Suites: 15"
                binding.tvPlatformName.text = "Airbnb"
            }

            else -> {
                // Optional default in case hotel is unknown
                binding.ivHotel.setImageResource(R.drawable.almas_hotel1)
                binding.tvOwner.text = "Owner: Unknown"
                binding.tvLocation.text = "Location: Unknown"
                binding.tvAddress.text = "Address: Unknown"
                binding.tvEmail.text = "Email: Unknown"
                binding.tvPhone.text = "Phone: Unknown"
                binding.tvAboutDescription.text = "No information available."
                binding.tvTotalRooms.text = "Total Rooms: 0"
                binding.tvDeluxeRooms.text = "Deluxe Rooms: 0"
                binding.tvSuites.text = "Suites: 0"
                binding.tvPlatformName.text = "None"
            }
        }

    }
}
