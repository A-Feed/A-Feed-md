package com.capstone.afeed.ui.fishpondform.temperaturesystemform

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.FragmentPhSystemFormBinding
import com.capstone.afeed.databinding.FragmentTemperatureSystemFormBinding
import com.capstone.afeed.ui.dialog.ErrorDialogFragment
import com.capstone.afeed.ui.dialog.SuccessDialogFragment
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.capstone.afeed.ui.fishpondform.phsystemform.PhSystemFormFragmentDirections

class TemperatureSystemFormFragment : Fragment() {
    private var _binding : FragmentTemperatureSystemFormBinding? = null
    private val binding get() = _binding!!
    private lateinit var temperatureSystemAdapter : TemperatureSystemAdapter
    private val fishPondFormViewModel: FishPondFormViewModel by activityViewModels {
        FishPondFormViewModelFactory.getInstance(
            requireContext()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTemperatureSystemFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeAdapter()
        setupAdapter()
        setupObserver()
        setupView()
        setupRecylerView()
    }

    private fun initializeAdapter() {
        temperatureSystemAdapter = TemperatureSystemAdapter()
    }

    private fun setupAdapter() {
        temperatureSystemAdapter.addNewListener = object : TemperatureSystemAdapter.AddNewListener{
            override fun insertItemListener(dataInput: FishpondIotRequest.Temperaturesystem, id: Int) {
                fishPondFormViewModel.editInputDataTemperatureSystem(id,dataInput)
            }

            override fun deleteItemListener(id: Int) {
                fishPondFormViewModel.deleteInputDataTemperatureSystem(id)
            }

        }
    }

    private fun setupObserver() {
        fishPondFormViewModel.fishpondTemperaturesystem.observe(requireActivity()){
            temperatureSystemAdapter.submitList(it.toList())
        }
    }

    private fun setupView() {
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
                textIndicatorView2.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView2.setImageResource(R.color.md_theme_primary)
                textIndicatorView3.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView3.setImageResource(R.color.md_theme_primary)
                textIndicatorView4.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView4.setImageResource(R.color.md_theme_primary)
            }
            btnAddInputTemperatureSystem.setOnClickListener {
                fishPondFormViewModel.addTemperatureSystem(
                    FishpondIotRequest.Temperaturesystem(temperatureSystemAdapter.itemCount, "")
                )
            }
            fabSave.setOnClickListener {
                Log.i("datas",
                    fishPondFormViewModel.fishpondData.value.toString())
                SuccessDialogFragment().show(requireActivity().supportFragmentManager, ErrorDialogFragment.TAG)
            }
        }
    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewFormTemperatureSystem.apply {
                adapter = temperatureSystemAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }
}