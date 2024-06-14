package com.capstone.afeed.ui.monitoring.detail.afeeding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.databinding.FragmentMonitoringAFeedingBinding
import com.capstone.afeed.ui.monitoring.MonitoringViewModel
import com.capstone.afeed.ui.monitoring.MonitoringViewModelFactory
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModel
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModelFactory

class MonitoringAFeedingFragment : Fragment() {

    private var _binding: FragmentMonitoringAFeedingBinding? = null
    private val binding get() = _binding!!
    private lateinit var monitoringAFeedingSystemAdapter: MonitoringAFeedingSystemAdapter
    private lateinit var monitoringAFeedingScheduleAdapter: MonitoringAFeedingScheduleAdapter
    private val monitoringDetailViewModel: MonitoringDetailViewModel by activityViewModels {
        MonitoringDetailViewModelFactory.getInstance(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonitoringAFeedingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        setupObserver()
        setupRecylerView()
    }

    private fun setupObserver() {
        with(monitoringDetailViewModel){
            if (fishPondId != null){
                getListAFeedingFromFishPondId(fishPondId).observe(viewLifecycleOwner) {
                    when(it){
                        is ResponseState.Error -> {
                            setupAtentionScheduleCard(View.VISIBLE,it.error)
                            setupAtentionAFeedingCard(View.VISIBLE,it.error)
                        }
                        ResponseState.Loading -> {}
                        is ResponseState.Success -> {
                            monitoringAFeedingSystemAdapter.submitList(it.data.data.listAFeedingSystem)
                            monitoringAFeedingScheduleAdapter.submitList(it.data.data.listSchedule)
                            if (it.data.data.listSchedule.isEmpty()){
                                setupAtentionScheduleCard(View.VISIBLE,getString(R.string.list_empty,"Schedule"))
                            }else{
                                setupAtentionScheduleCard(View.GONE)
                            }
                            if (it.data.data.listAFeedingSystem.isEmpty()){
                                setupAtentionAFeedingCard(View.VISIBLE,getString(R.string.list_empty,"A Feeding"))
                            }else{
                                setupAtentionAFeedingCard(View.GONE)
                            }
                        }
                    }
                }
            }else{

            }

        }

    }

    private fun setupAtentionScheduleCard(visibilty : Int,message : String? = ""){
        with(binding){
            attentionWrapperSchedule.visibility = visibilty
            attentionIncludedSchedule.apply {
                textViewAttentionMessage.setText(message)
            }
        }
    }

    private fun setupAtentionAFeedingCard(visibilty : Int,message : String? = ""){
        with(binding){
            attentionWrapperAfeedingSystem.visibility = visibilty
            attentionIncludedAfeeding.apply {
                textViewAttentionMessage.setText(message)
            }
        }
    }


    private fun setupRecylerView() {
        with(binding) {
            recylerViewMonitoringAFeedingSystem.apply {
                adapter = monitoringAFeedingSystemAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
            recylerViewMonitoringAFeedingSchedule.apply {
                adapter = monitoringAFeedingScheduleAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }
        }
    }


    private fun setupAdapter() {
        monitoringAFeedingSystemAdapter = MonitoringAFeedingSystemAdapter()
        monitoringAFeedingScheduleAdapter = MonitoringAFeedingScheduleAdapter()
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}