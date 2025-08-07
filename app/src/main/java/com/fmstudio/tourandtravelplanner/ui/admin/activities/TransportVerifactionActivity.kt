package com.fmstudio.tourandtravelplanner.ui.admin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityTransportVerifactionBinding

class TransportVerifactionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransportVerifactionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransportVerifactionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.ivBack.setOnClickListener {
            finish()
        }


        val transportName = intent.getStringExtra("TRANSPORT_NAME") ?: "Unknown Transport"

        // Set screen title & transport name
        binding.tvTitle.text = "Transport Verification"
        binding.tvTransportName.text = transportName

        // Populate mock data
        when (transportName) {
            "SkyLine Travels" -> {
                binding.ivVehicle.setImageResource(R.drawable.ic_bus1)
                binding.tvOwner.text = "Owner: Mr. Ali Khan"
                binding.tvVehicleRegistration.text = "Registration No: LEB-1234"
                binding.tvVehicleType.text = "Vehicle Type: Hiace"
                binding.tvVehicleSeats.text = "Seats: 15"
                binding.tvOperatingCity.text = "Operating City: Swat"
                binding.tvEmail.text = "Email: ali.khan@example.com"
                binding.tvPhone.text = "Phone: +92 300 1234567"
                binding.tvPlatformName.text = "Registered on: BookMyBus"
                binding.ivCnic.setImageResource(R.drawable.ic_cnic)
                binding.ivRegistrationDoc.setImageResource(R.drawable.ic_doc)
            }

            "Mountain Movers" -> {
                binding.ivVehicle.setImageResource(R.drawable.ic_coaster)
                binding.tvOwner.text = "Owner: Ms. Sara Baloch"
                binding.tvVehicleRegistration.text = "Registration No: ISB-777"
                binding.tvVehicleType.text = "Vehicle Type: Coaster"
                binding.tvVehicleSeats.text = "Seats: 22"
                binding.tvOperatingCity.text = "Operating City: Islamabad"
                binding.tvEmail.text = "Email: sara.baloch@example.com"
                binding.tvPhone.text = "Phone: +92 321 8765432"
                binding.tvPlatformName.text = "Registered on: RoadTrip"
                binding.ivCnic.setImageResource(R.drawable.ic_cnic)
                binding.ivRegistrationDoc.setImageResource(R.drawable.ic_doc)
            }

            "HighRoad Express" -> {
                binding.ivVehicle.setImageResource(R.drawable.ic_bus2)
                binding.tvOwner.text = "Owner: Mr. Imran Baig"
                binding.tvVehicleRegistration.text = "Registration No: HZ-1122"
                binding.tvVehicleType.text = "Vehicle Type: Mini Bus"
                binding.tvVehicleSeats.text = "Seats: 30"
                binding.tvOperatingCity.text = "Operating City: Hunza"
                binding.tvEmail.text = "Email: imran.baig@example.com"
                binding.tvPhone.text = "Phone: +92 313 4455667"
                binding.tvPlatformName.text = "Registered on: GoWheels"
                binding.ivCnic.setImageResource(R.drawable.ic_cnic)
                binding.ivRegistrationDoc.setImageResource(R.drawable.ic_doc)
            }

            else -> {
                binding.tvOwner.text = "Owner: Unknown"
                binding.tvVehicleRegistration.text = "Registration No: -"
                binding.tvVehicleType.text = "Vehicle Type: -"
                binding.tvVehicleSeats.text = "Seats: -"
                binding.tvOperatingCity.text = "Operating City: -"
                binding.tvEmail.text = "Email: -"
                binding.tvPhone.text = "Phone: -"
                binding.tvPlatformName.text = "Registered on: -"
            }
        }
    }
}
