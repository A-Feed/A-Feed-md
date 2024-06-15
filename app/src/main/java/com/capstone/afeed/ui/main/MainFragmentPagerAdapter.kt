package com.capstone.afeed.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.afeed.ui.main.dashboard.DashboardFragment
import com.capstone.afeed.ui.main.home.HomeFragmentBeforeLogIn
import com.capstone.afeed.ui.main.settings.SettingsFragment

class MainFragmentPagerAdapter(val activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = fragmentsScreen.size
    override fun createFragment(position: Int): Fragment = fragmentsScreen[position]

    companion object{
        val fragmentsScreen = listOf<Fragment>(
            HomeFragmentBeforeLogIn(),
            DashboardFragment(),
            SettingsFragment()
        )
    }

}