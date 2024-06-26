package com.capstone.afeed.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.capstone.afeed.R
import com.capstone.afeed.databinding.ActivityMainBinding
import com.capstone.afeed.ui.notification.NotificationActivity

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
        setupView()
    }

    private val bottomNavigationMenuId = listOf(
        R.id.menu_home,
        R.id.menu_dashboard,
        R.id.menu_settings,
    )

    private fun setupView() {
        with(binding) {
            viewpagerMain.apply {
                adapter = MainFragmentPagerAdapter(this@MainActivity)
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        bottomNavigation.selectedItemId = bottomNavigationMenuId[position]
                    }
                })
            }
            bottomNavigation.setOnItemSelectedListener {
                val checkClickedMenu = bottomNavigationMenuId.indexOf(it.itemId)
                viewpagerMain.currentItem = checkClickedMenu
                return@setOnItemSelectedListener true
            }
            includedToolbarMain.tollbarMain.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_notification -> {
                        val intent = Intent(this@MainActivity, NotificationActivity::class.java)
                        startActivity(intent)
                        return@setOnMenuItemClickListener true
                    }

                    else -> false
                }
            }
        }
    }


}