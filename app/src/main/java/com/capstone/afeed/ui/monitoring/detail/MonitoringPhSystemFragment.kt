package com.capstone.afeed.ui.monitoring.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.afeed.R
import com.capstone.afeed.databinding.FragmentMonitoringAFeedingBinding
import com.capstone.afeed.databinding.FragmentMonitoringPhSystemBinding

class MonitoringPhSystemFragment : Fragment() {

    private var _binding: FragmentMonitoringPhSystemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}