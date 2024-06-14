package com.capstone.afeed.ui.monitoring.detail

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.afeed.ui.monitoring.detail.afeeding.MonitoringAFeedingFragment
import com.capstone.afeed.ui.monitoring.detail.phsystem.MonitoringPhSystemFragment
import com.capstone.afeed.ui.monitoring.detail.temperaturesystem.MonitoringTemperatureSystemFragment

class MonitoringSystemFragmentAdapter(private val activity : AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MonitoringAFeedingFragment()
            1 -> MonitoringPhSystemFragment()
            2 -> MonitoringTemperatureSystemFragment()
            else -> {
                MonitoringAFeedingFragment()
            }
        }
    }
}