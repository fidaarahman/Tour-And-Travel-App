package com.fmstudio.tourandtravelplanner.ui.user.activities

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityMainBinding
import com.fmstudio.tourandtravelplanner.helper.AppController
import com.fmstudio.tourandtravelplanner.ui.user.fragments.BookingFragment
import com.fmstudio.tourandtravelplanner.ui.user.fragments.ProfileFragment
import com.fmstudio.tourandtravelplanner.ui.user.fragments.TripPlaningFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(HomeFragment())
                R.id.nav_trip -> loadFragment(TripPlaningFragment())
                R.id.nav_booking -> loadFragment(BookingFragment())
                R.id.nav_profile -> loadFragment(ProfileFragment())
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()

        if (fragment is HomeFragment) {
            binding.iconSearch.visibility = View.VISIBLE
            binding.iconProfile.visibility = View.VISIBLE
        } else {
            binding.iconSearch.visibility = View.GONE
            binding.iconProfile.visibility = View.GONE
        }
    }
    override fun attachBaseContext(newBase: Context) {
        val context = AppController.setAppLocale(newBase)
        super.attachBaseContext(context)
    }
}
