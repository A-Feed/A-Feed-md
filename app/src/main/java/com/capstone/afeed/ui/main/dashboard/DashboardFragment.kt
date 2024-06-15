package com.capstone.afeed.ui.main.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.adapter.ServiceNavigationAdapter
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.local.model.NavigationWithIcon
import com.capstone.afeed.data.remote.response.GetListRegisteredFishpondWithSystemMeanScore
import com.capstone.afeed.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding : FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var fispondSystemMeanValueAdapter: FispondSystemMeanValueAdapter
    private val dashboardViewModel : DashboardViewModel by viewModels {
        DashboardViewModelFactory.getInstance(requireContext())
    }
    private lateinit var serviceNavigationAdapter : ServiceNavigationAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupAdapterToView()
//        setupObserver()
        setupDummyData()
    }

    private fun setupDummyData() {
        fispondSystemMeanValueAdapter.submitList(listOf(
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData("9/10","Ini description","Fihspond ini namanya","1","7-8","23-22"),
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData("","","","","",""),
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData("","","","","",""),
        ))
        serviceNavigationAdapter.submitList(
            listOf(
                NavigationWithIcon(1,R.drawable.home_fill,null,"Title ini","ini descriptionnya cccccc"),
                NavigationWithIcon(1,R.drawable.home_fill,null,"Title ini","ini descriptionnya cccccc"),
                NavigationWithIcon(1,R.drawable.home_fill,null,"Title ini","ini descriptionnya cccccc"),
                )
        )
    }

    private fun setupObserver() {
        dashboardViewModel.getListRegisteredFishpondWithSystemMeanScore(1).observe(requireActivity()){
            when(it){
                is ResponseState.Error -> {

                }
                ResponseState.Loading -> {

                }
                is ResponseState.Success -> {
                    fispondSystemMeanValueAdapter.submitList(it.data.data)
                }
            }
        }
    }

    private fun setupAdapterToView() {
        with(binding){
            viewPagerListFishpondWithMeanData.apply {
                adapter = fispondSystemMeanValueAdapter
            }
            recylerViewServiceNavigation.apply {
                adapter = serviceNavigationAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private fun setupAdapter() {
        fispondSystemMeanValueAdapter = FispondSystemMeanValueAdapter()
        serviceNavigationAdapter = ServiceNavigationAdapter()

    }



}