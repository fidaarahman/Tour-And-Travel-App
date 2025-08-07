package com.fmstudio.tourandtravelplanner.ui.user.activities

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.adapters.HotelImagesAdapter
import com.fmstudio.tourandtravelplanner.databinding.ActivityShowHotelDetailBinding
import com.fmstudio.tourandtravelplanner.helper.SessionManager
import com.google.android.gms.common.util.CollectionUtils.listOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ShowHotelDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowHotelDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowHotelDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hotel Name from intent
        val hotelName = intent.getStringExtra("hotel_name") ?: "Hotel"
        binding.tvHotelName.text = hotelName

        // Load guest and date info from SessionManager
        val checkIn = SessionManager.getString(SessionManager.KEY_CHECK_IN_DATE, "Check-in")
        val checkOut = SessionManager.getString(SessionManager.KEY_CHECK_OUT_DATE, "Check-out")
        val rooms = SessionManager.getString(SessionManager.KEY_ROOMS, "0")
        val adults = SessionManager.getString(SessionManager.KEY_ADULTS, "0")

        binding.tvDates.text = "$checkIn - $checkOut"
        binding.tvGuests.text = "$adults adults . $rooms room"

        // Room Types for user selection
        val roomTypes = listOf(
            "Select Room Type",
            "Double Bed Room",
            "Deluxe Triple Room",
            "Family Room with Bathroom",
            "Double Room with Garden"
        )

        // Spinner Adapter (no default selection logic)
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            roomTypes
        )
        binding.spinnerRoomType.adapter = spinnerAdapter

        // Optional: Show selection toast
        binding.spinnerRoomType.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                val selected = roomTypes[position]
                if (position != 0) {
                    Toast.makeText(this@ShowHotelDetailActivity, "Selected: $selected", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: android.widget.AdapterView<*>) {}
        })

        binding.ivBack.setOnClickListener {
            finish()
        }
        // Reserve Button
        binding.btSearch.setOnClickListener {
            val user = FirebaseAuth.getInstance().currentUser
            val email = user?.email

            if (email == null) {
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val userId = email.replace(".", "_")

            FirebaseFirestore.getInstance().collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val name = document.getString("name") ?: ""
                        val phone = document.getString("phone") ?: ""

                        SessionManager.setString("user_name", name)
                        SessionManager.setString("user_email", email)
                        SessionManager.setString("user_phone", phone)

                        Toast.makeText(
                            this,
                            "Reservation sent! You'll be notified once it's verified.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(this, "User data not found", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to fetch user info: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }


        // Hotel Images
        val imageList = listOf(
            getDrawableUri(R.drawable.almas_hotel1),
            getDrawableUri(R.drawable.almas_hotel2),
            getDrawableUri(R.drawable.almas_hotel3),
            getDrawableUri(R.drawable.almas_hotel4),
            getDrawableUri(R.drawable.almas_hotel5),
            getDrawableUri(R.drawable.almas_hotel6)
        )

        val hotelImagesAdapter = HotelImagesAdapter(this, imageList) { clickedPosition ->
            Toast.makeText(this, "Clicked image $clickedPosition", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerHotelImages.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerHotelImages.adapter = hotelImagesAdapter
    }

    private fun getDrawableUri(drawableResId: Int): String {
        return "android.resource://${packageName}/$drawableResId"
    }
}
