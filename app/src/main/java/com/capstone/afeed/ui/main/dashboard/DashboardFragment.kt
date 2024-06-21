package com.capstone.afeed.ui.main.dashboard

import android.content.Intent
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
import com.capstone.afeed.databinding.ListColumnNavigationCardBinding
import com.capstone.afeed.ui.fishpondform.FishPondFormActivity
import com.capstone.afeed.ui.monitoring.MonitoringActivity

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
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData(
                "3/3","This fishpond is on the side of the statue","Fishpond John Ace","1","7,6","28,0"),
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData("","","","","",""),
            GetListRegisteredFishpondWithSystemMeanScore.FishpondWithMeanSystemData("","","","","",""),
        ))

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
        serviceNavigationAdapter.submitList(
            listOf(
                NavigationWithIcon(1,R.drawable.ic_monitoring,null,"Monitoring","monitoring your registered IOT system"),
                NavigationWithIcon(2,R.drawable.settings_fill,null,"Setting IOT system","adjust setting for you IOT system"),
                NavigationWithIcon(3,R.drawable.ic_form,null,"Registering New FishPond","setup and register your new bought IOT to app"),
            )
        )
        serviceNavigationAdapter.addNewListener = object : ServiceNavigationAdapter.AddNewListener{
            override fun onClickListener(
                item: NavigationWithIcon,
                binding: ListColumnNavigationCardBinding
            ) {
                val intentClass = when (item.id){
                    1 -> {
                         Intent(requireContext(),MonitoringActivity::class.java)
                    }
                    2 -> {
                        Intent(requireContext(),FishPondFormActivity::class.java).putExtra(FishPondFormActivity.FORM_MODE_EXTRAS,FishPondFormActivity.FORM_MODE_EDIT)
                    }
                    3-> {
                        Intent(requireContext(),FishPondFormActivity::class.java).putExtra(FishPondFormActivity.FORM_MODE_EXTRAS,FishPondFormActivity.FORM_MODE_ADD)
                    }
                    else -> {
                        Intent(requireContext(),MonitoringActivity::class.java)
                    }
                }
                startActivity(intentClass)
            }

        }

    }



}