package com.fmstudio.tourandtravelplanner.ui.manager.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityManagerMainBinding

class ManagerMainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityManagerMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityManagerMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val navController = findNavController(R.id.manager_nav_host_fragment)
		binding.bottomNavigation.setupWithNavController(navController)
	}
}