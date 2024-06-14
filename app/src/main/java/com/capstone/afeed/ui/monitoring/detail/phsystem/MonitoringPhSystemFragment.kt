package com.capstone.afeed.ui.monitoring.detail.phsystem

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
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModel
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailViewModelFactory

class MonitoringPhSystemFragment : Fragment() {

    private var _binding: FragmentMonitoringPhSystemBinding? = null
    private val binding get() = _binding!!
    private lateinit var monitoringPhSystemAdapter: MonitoringPhSystemAdapter
    private val monitoringDetailViewModel: MonitoringDetailViewModel by activityViewModels {
        MonitoringDetailViewModelFactory.getInstance(
            requireContext()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonitoringPhSystemBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupObserver()
        setupRecylerView()
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewMonitoringPhSystem.apply {
                adapter = monitoringPhSystemAdapter
                layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            }
        }
    }

    private fun setupObserver() {
        with(monitoringDetailViewModel){
            fishPondId?.let {
                getListPhSystemFromFishPondID(it).observe(viewLifecycleOwner){
                    when(it){
                        is ResponseState.Error -> {
                            setupAtentionPhSystemCard(View.VISIBLE,it.error)
                        }
                        ResponseState.Loading -> {}
                        is ResponseState.Success -> {
                            monitoringPhSystemAdapter.submitList(it.data.data)
                            if (it.data.data.isEmpty()){
                                setupAtentionPhSystemCard(View.VISIBLE,getString(R.string.list_empty,"Ph System"))
                            }else{
                                setupAtentionPhSystemCard(View.GONE)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setupAtentionPhSystemCard(visibilty : Int,message : String? = ""){
        with(binding){
            attentionWrapperPhSystem.visibility = visibilty
            attentionIncludedCardPh.apply {
                textViewAttentionMessage.setText(message)
            }
        }
    }

    private fun setupAdapter() {
        monitoringPhSystemAdapter = MonitoringPhSystemAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}