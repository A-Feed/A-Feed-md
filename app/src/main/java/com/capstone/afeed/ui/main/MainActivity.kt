package com.capstone.afeed.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.ActivityMainBinding
import com.capstone.afeed.ui.main.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainFragment(HomeFragmentBeforeLogIn())

        binding.bottomNavigation.setOnItemSelectedListener { menu ->
            when (menu.itemId) {
                R.id.menu_home -> mainFragment(HomeFragmentBeforeLogIn())
                R.id.menu_dashboard -> mainFragment(DashboardFragment())
                R.id.menu_settings -> mainFragment(SettingsFragment())
            }
            true
        }

    }

    private fun mainFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(binding.fragmentMain.id, fragment)
        fragmentTransition.commit()
    }
}