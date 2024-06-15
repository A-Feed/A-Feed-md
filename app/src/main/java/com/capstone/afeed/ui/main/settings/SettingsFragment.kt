package com.capstone.afeed.ui.main.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.adapter.ServiceNavigationAdapter
import com.capstone.afeed.data.local.model.NavigationWithIcon
import com.capstone.afeed.databinding.FragmentDashboardBinding
import com.capstone.afeed.databinding.FragmentSettingsBinding
import com.capstone.afeed.databinding.ListColumnNavigationCardBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var appSettingsAdapter: ServiceNavigationAdapter
    private lateinit var accountSettingsAdapter: ServiceNavigationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapter()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        with(binding){
            recylerViewAppSettingsSettings.apply {
                adapter = appSettingsAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
            recylerViewAccountSettingsSettings.apply {
                adapter = accountSettingsAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private val accountSettingsMenu = listOf(
        NavigationWithIcon(
            1,
            R.drawable.ic_user_fill,
            null,
            "Profile",
            "Edit your profile right here"
        ),
        NavigationWithIcon(
            2,
            R.drawable.ic_password,
            null,
            "Change Password",
            "Change your password easy using old password"
        )
    )

    private val accountSettingsMenuIntent = listOf(
        null
    )

    private val appSettingsMenu = listOf(
        NavigationWithIcon(
            1,
            null,
            R.drawable.ic_dropdown,
            "Theme App",
            "Chose you prefered theme for the app here"
        ),
        NavigationWithIcon(
            2,
            null,
            R.drawable.ic_dropdown,
            "Language",
            "Chose you prefered language here"
        )
    )

    private fun setupAdapter() {
        val daya = SettingsFragment::class.java

        accountSettingsAdapter = ServiceNavigationAdapter()
        accountSettingsAdapter.submitList(accountSettingsMenu)
        accountSettingsAdapter.addNewListener = object :ServiceNavigationAdapter.AddNewListener{
            override fun onClickListener(
                item: NavigationWithIcon,
                binding: ListColumnNavigationCardBinding
            ) {
            }
        }
        appSettingsAdapter = ServiceNavigationAdapter()
        appSettingsAdapter.submitList(appSettingsMenu)
        appSettingsAdapter.addNewListener = object :ServiceNavigationAdapter.AddNewListener{
            override fun onClickListener(
                item: NavigationWithIcon,
                binding: ListColumnNavigationCardBinding
            ) {

            }
        }
    }

}