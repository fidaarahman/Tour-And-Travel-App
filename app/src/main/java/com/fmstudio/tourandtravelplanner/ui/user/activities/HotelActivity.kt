package com.fmstudio.tourandtravelplanner.ui.user.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fmstudio.tourandtravelplanner.databinding.ActivityHotelBinding
import com.fmstudio.tourandtravelplanner.helper.SessionManager
import java.text.SimpleDateFormat
import java.util.*

class HotelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHotelBinding
    private var checkInDate: Calendar? = null
    private var checkOutDate: Calendar? = null

    companion object {
        const val CITY_REQUEST_CODE = 1001



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // City selection
        binding.selectCityContainer.setOnClickListener {
            val intent = Intent(this, SelectCityActivity::class.java)
            startActivityForResult(intent, CITY_REQUEST_CODE)
        }

        // Date pickers
        binding.checkInContainer.setOnClickListener { showCheckInDatePicker() }
        binding.checkOutContainer.setOnClickListener { showCheckOutDatePicker() }

        // Search button
        binding.btSearch.setOnClickListener {
            val city = binding.tvSelectedCity.text.toString().trim()
            val checkIn = binding.tvCheckIn.text.toString().trim()
            val checkOut = binding.tvCheckOut.text.toString().trim()
            val rooms = binding.etRoom.text.toString().trim()
            val adults = binding.etAdults.text.toString().trim()
            val children = binding.etChildren.text.toString().trim()
            val babies = binding.etInfants.text.toString().trim()

            // Input validation
            when {
                city.isEmpty() || city == "Select City" -> showToast("Please select a city.")
                checkIn.isEmpty() || checkIn == "Check-in" -> showToast("Please select check-in date.")
                checkOut.isEmpty() || checkOut == "Check-out" -> showToast("Please select check-out date.")
                rooms.isEmpty() -> showToast("Please enter number of rooms.")
                adults.isEmpty() -> showToast("Please enter number of adults.")
                children.isEmpty() -> showToast("Please enter number of children.")
                babies.isEmpty() -> showToast("Please enter number of babies.")
                else -> {
                    // Save to session
                    SessionManager.setString(SessionManager.KEY_SELECTED_CITY, city)
                    SessionManager.setString(SessionManager.KEY_CHECK_IN_DATE, checkIn)
                    SessionManager.setString(SessionManager.KEY_CHECK_OUT_DATE, checkOut)
                    SessionManager.setString(SessionManager.KEY_ROOMS, rooms)
                    SessionManager.setString(SessionManager.KEY_ADULTS, adults)
                    SessionManager.setString(SessionManager.KEY_CHILDREN, children)
                    SessionManager.setString(SessionManager.KEY_BABIES, babies)

                    // Navigate
                    startActivity(Intent(this, ShowHotelsActivity::class.java))
                }
            }
        }
        binding.backButton.setOnClickListener{
            finish()
        }
    }

    private fun showCheckInDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                checkInDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                val formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    .format(checkInDate!!.time)
                binding.tvCheckIn.text = formattedDate

                checkOutDate?.let {
                    if (it.before(checkInDate)) {
                        binding.tvCheckOut.text = "Check-out"
                        checkOutDate = null
                    }
                }
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.datePicker.minDate = System.currentTimeMillis()
        datePicker.show()
    }
    private fun showCheckOutDatePicker() {
        val calendar = Calendar.getInstance()

        val minDate = checkInDate?.apply { add(Calendar.DAY_OF_MONTH, 1) }?.timeInMillis
            ?: System.currentTimeMillis()

        val datePicker = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                checkOutDate = Calendar.getInstance().apply {
                    set(year, month, dayOfMonth)
                }
                val formattedDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    .format(checkOutDate!!.time)
                binding.tvCheckOut.text = formattedDate
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.datePicker.minDate = minDate
        datePicker.show()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CITY_REQUEST_CODE && resultCode == RESULT_OK) {
            val selectedCity = data?.getStringExtra("selected_city")
            selectedCity?.let {
                binding.tvSelectedCity.text = it
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
