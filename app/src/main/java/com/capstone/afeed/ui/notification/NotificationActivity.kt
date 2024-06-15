package com.capstone.afeed.ui.notification

import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.response.NotificationResponse
import com.capstone.afeed.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationBinding
    private lateinit var notificationAdapter: NotificationAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapter()
        setupRecyclerView()
        setupObserver()
        setupDummy()
    }

    private fun setupDummy() {
        notificationAdapter.submitList(listOf(
            NotificationResponse("TITleNya ini","message body coy","success"),
            NotificationResponse("TITleNya ini 3","message body coy","success"),
            NotificationResponse("TITleNya ini 5","message body coy","success"),

        ))
    }

    private fun setupRecyclerView() {
        with(binding){
            recylerViewNotification.apply {
                adapter = notificationAdapter
                layoutManager = LinearLayoutManager(this@NotificationActivity,LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private fun setupAdapter() {
        notificationAdapter = NotificationAdapter()
    }

    private fun setupObserver() {
    }

}