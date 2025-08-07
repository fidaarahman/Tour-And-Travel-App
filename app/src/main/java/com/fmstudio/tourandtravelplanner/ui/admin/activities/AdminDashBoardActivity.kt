package com.fmstudio.tourandtravelplanner.ui.admin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.fmstudio.tourandtravelplanner.R
import com.fmstudio.tourandtravelplanner.databinding.ActivityAdminDashBoardBinding
import com.fmstudio.tourandtravelplanner.ui.admin.fragments.AdminHomeFragment
import com.fmstudio.tourandtravelplanner.ui.admin.fragments.AdminListFragment

class AdminDashBoardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashBoardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            loadFragment(AdminHomeFragment())
            binding.tvAdminTitle.text = "Admin DashBoard"
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(AdminHomeFragment())
                    binding.tvAdminTitle.text = "Admin DashBoard"      // Show Home title

                }
                R.id.nav_list -> {
                    loadFragment(AdminListFragment())
                    binding.tvAdminTitle.text = "List "      // Change title to "List"
                    // or use View.GONE if you want to hide: binding.tvTitle.visibility = View.GONE
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
