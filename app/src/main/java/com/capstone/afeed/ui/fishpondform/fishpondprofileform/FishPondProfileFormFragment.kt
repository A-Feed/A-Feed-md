package com.capstone.afeed.ui.fishpondform.fishpondprofileform

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.FragmentFishPondProfileFormBinding
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory

class FishPondProfileFormFragment : Fragment() {

    private var _binding : FragmentFishPondProfileFormBinding? = null
    private val binding get() = _binding!!
    private val fishPondFormViewModel: FishPondFormViewModel by activityViewModels {
        FishPondFormViewModelFactory.getInstance(
            requireContext()
        )
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFishPondProfileFormBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private fun setupView() {
        var fishpondName = ""
        var fishpondDescription = ""
        var fishType = ""
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(requireContext().getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
            }
            inputTextFishpondName.addTextChangedListener {
                fishpondName = it.toString()
            }
            inputTextFishpondDescription.addTextChangedListener {
                fishpondDescription = it.toString()
            }
            setupFishTypeSpinner(spinnerFishType){
                fishType = it
            }
            fabNext.setOnClickListener {
                sendInputdataToViewmodel(
                    FishpondIotRequest.Fishpond(
                        fishpondDescription,
                        fishpondName,
                        fishType
                    )
                )
                findNavController().navigate(FishPondProfileFormFragmentDirections.actionFishPondProfileFormFragmentToAFeedingFormFragment())
            }
        }
    }

    private fun setupFishTypeSpinner(spinnerFishType: Spinner, selectedValueListener :(selectedValue : String)-> Unit) {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.fish_type_spinner,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinnerFishType.adapter = adapter
        }

        spinnerFishType.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.getItemAtPosition(position).toString()
                if (position == 0) {
                    // This is the placeholder item
                    Toast.makeText(requireContext(), "Please select an item", Toast.LENGTH_SHORT).show()
                } else {
                    selectedValueListener(item)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }

    private fun sendInputdataToViewmodel(fishpond: FishpondIotRequest.Fishpond) {
        fishPondFormViewModel.saveFishpondProfile(fishpond)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}