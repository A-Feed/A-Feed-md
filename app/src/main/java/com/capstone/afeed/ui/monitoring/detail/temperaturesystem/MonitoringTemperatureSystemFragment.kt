package com.capstone.afeed.ui.monitoring.detail.temperaturesystem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.databinding.FragmentMonitoringAFeedingBinding
import com.capstone.afeed.databinding.FragmentMonitoringPhSystemBinding
import com.capstone.afeed.databinding.FragmentMonitoringTemperatureSystemBinding
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModel
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModelFactory

class MonitoringTemperatureSystemFragment : Fragment() {

    private var _binding: FragmentMonitoringTemperatureSystemBinding? = null
    private val binding get() = _binding!!
    private lateinit var monitoringTemperatureSystemAdapter: MonitoringTemperatureSystemAdapter
    private val monitoringDetailViewModel: MonitoringDetailViewModel by activityViewModels {
        MonitoringDetailViewModelFactory.getInstance(
            requireContext()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonitoringTemperatureSystemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupRecylerView()
        setupObserver()
    }

    private fun setupObserver() {
        with(monitoringDetailViewModel){
            getListTemperatureSystemFromFishPondID(this@with.fishPondId).observe(viewLifecycleOwner){
                when(it){
                    is ResponseState.Error -> {
                        setupAtentionTmeperatureSystemCard(View.VISIBLE,it.error)
                    }
                    ResponseState.Loading -> {}
                    is ResponseState.Success -> {
                        monitoringTemperatureSystemAdapter.submitList(it.data.data)
                        if (it.data.data.isEmpty()){
                            setupAtentionTmeperatureSystemCard(View.VISIBLE,getString(R.string.list_empty,"Temperature"))
                        }else{
                            setupAtentionTmeperatureSystemCard(View.GONE)
                        }
                    }
                }
            }
        }
    }

    private fun setupAtentionTmeperatureSystemCard(visibilty : Int,message : String? = ""){
        with(binding){
            attentionWrapperTemperatureSystem.visibility = visibilty
            attentionIncludedCardTemperature.apply {
                textViewAttentionMessage.setText(message)
            }
        }
    }

    private fun setupAdapter() {
        monitoringTemperatureSystemAdapter = MonitoringTemperatureSystemAdapter()
    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewMonitoringTemperatureSystem.apply {
                adapter = monitoringTemperatureSystemAdapter
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}