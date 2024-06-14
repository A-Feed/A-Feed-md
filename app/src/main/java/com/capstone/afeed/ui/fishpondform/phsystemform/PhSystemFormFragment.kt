package com.capstone.afeed.ui.fishpondform.phsystemform

import android.os.Bundle
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
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory

class PhSystemFormFragment : Fragment() {


    private var _binding : FragmentPhSystemFormBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var phSystemAdapter: PhSystemAdapter
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
        _binding = FragmentPhSystemFormBinding.inflate(inflater, container, false)
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

    private fun setupObserver() {
        fishPondFormViewModel.fishpondPhsystem.observe(requireActivity()){
            phSystemAdapter.submitList(it.toList())
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
            }
            btnAddPhSystem.setOnClickListener {
                fishPondFormViewModel.addPhSystem(
                    FishpondIotRequest.Phsystem(phSystemAdapter.itemCount, "")
                )
            }
            fabNext.setOnClickListener {
                findNavController().navigate(PhSystemFormFragmentDirections.actionPhSystemFormFragmentToTemperatureSystemFormFragment())
            }
        }
    }

    private fun initializeAdapter() {
        phSystemAdapter = PhSystemAdapter()

    }

    private fun setupAdapter(){
        phSystemAdapter.addNewListener = object : PhSystemAdapter.AddNewListener {
            override fun insertItemListener(dataInput: FishpondIotRequest.Phsystem, id: Int) {
                fishPondFormViewModel.editInputDataPhSystem(id, dataInput)
            }

            override fun deleteItemListener(id: Int) {
                fishPondFormViewModel.deleteInputDataPhSystem(id)
            }

        }
    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewPhSystem.apply {
                adapter = phSystemAdapter
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    
}