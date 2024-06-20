package com.capstone.afeed.ui.fishpondform.afeedingform

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
import com.capstone.afeed.databinding.FragmentAFeedingFormBinding
import com.capstone.afeed.ui.dialog.TimePickerDialogFragment
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class AFeedingFormFragment : Fragment() {
    private var _binding: FragmentAFeedingFormBinding? = null
    private val binding get() = _binding!!
    private val fishPondFormViewModel: FishPondFormViewModel by activityViewModels {
        FishPondFormViewModelFactory.getInstance(
            requireContext()
        )
    }
    private lateinit var aFeedingScheduleAdapter: AFeedingScheduleAdapter
    private lateinit var aFeedingSystemAdapter: AFeedingSystemAdapter
    private val timePicker: MaterialTimePicker by lazy {
        MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(10)
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .setTitleText(getString(R.string.select_feeding_time))
            .build()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAFeedingFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initializeAdapter()
        setupAdapter()
        setupObserver()
        setupView()
        setupRecylerView()
    }


    private fun setupAdapter() {
        aFeedingScheduleAdapter.addNewListener = object : AFeedingScheduleAdapter.AddNewListener {
            override fun inputDataListener(
                dataInput: FishpondIotRequest.AFeedingSchedule,
                id: Int
            ) {
                fishPondFormViewModel.editInputDataAFeedingSchedule(id, dataInput)
            }

            override fun deleteItemListener(id: Int) {
                fishPondFormViewModel.deleteInputDataAFeedingSchedule(id)
            }

        }
        aFeedingSystemAdapter.addNewListener = object : AFeedingSystemAdapter.AddNewListener {
            override fun insertItemListener(
                dataInput: FishpondIotRequest.AFeedingSystem,
                id: Int
            ) {
                Log.i("datas", id.toString())
                fishPondFormViewModel.editInputDataAFeedingSystem(id, dataInput)
            }

            override fun deleteItemListener(id: Int) {
                fishPondFormViewModel.deleteInputDataAFeedingSystem(id)
            }
        }
    }

    private fun initializeAdapter() {
        aFeedingScheduleAdapter = AFeedingScheduleAdapter(this)
        aFeedingSystemAdapter = AFeedingSystemAdapter()
    }

    private fun setupView() {
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
                textIndicatorView2.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView2.setImageResource(R.color.md_theme_primary)
            }
            btnAddAFeedingSchedule.setOnClickListener {
                addAfeedingInput()
            }
            btnAddAFeedingSystem.setOnClickListener {
                addAfeedingSystem()
            }
            fabNext.setOnClickListener {
                findNavController().navigate(AFeedingFormFragmentDirections.actionAFeedingFormFragmentToPhSystemFormFragment())
            }
        }
    }

    private fun setupObserver() {
        with(fishPondFormViewModel) {
            fishpondAFeedingSchedule.observe(requireActivity()) {
//                Log.i("datas",it.toList().toString())
                aFeedingScheduleAdapter.submitList(it.toList())
            }
            fishpondAFeedingSystem.observe(requireActivity()) {
                aFeedingSystemAdapter.submitList(it.toList())
            }
            fishpondData.observe(requireActivity()) {
                aFeedingSystemAdapter.submitList(it.afeeding?.aFeedingSystem?.toList())
            }
        }

    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewAFeedingSchedule.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = aFeedingScheduleAdapter
            }
            recylerViewAFeedingSystem.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = aFeedingSystemAdapter
            }
        }
    }


    private fun addAfeedingInput() {
        fishPondFormViewModel.addAFeedingSchedule(
            FishpondIotRequest.AFeedingSchedule(aFeedingScheduleAdapter.itemCount, "", "")
        )
    }

    private fun addAfeedingSystem() {
        fishPondFormViewModel.addAFeedingSystem(
            FishpondIotRequest.AFeedingSystem(aFeedingSystemAdapter.itemCount, "")
        )
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}