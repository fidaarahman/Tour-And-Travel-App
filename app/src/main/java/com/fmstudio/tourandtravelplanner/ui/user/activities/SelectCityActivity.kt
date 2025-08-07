package com.fmstudio.tourandtravelplanner.ui.user.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fmstudio.tourandtravelplanner.databinding.ActivitySelectCityBinding
import com.fmstudio.tourandtravelplanner.ui.adapters.DisplayCityAdapter

import com.google.android.gms.common.util.CollectionUtils.listOf

class SelectCityActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectCityBinding

    private val cityList = listOf(
        "Swat","Murrie","Lahore", "Rawalpindi","Skardu","Malluma Jaba"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectCityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewCities.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCities.adapter = DisplayCityAdapter(cityList) { selectedCity ->
            val resultIntent = Intent()
            resultIntent.putExtra("selected_city", selectedCity)
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        binding.backArrow.setOnClickListener {
            finish()
        }
    }
}
