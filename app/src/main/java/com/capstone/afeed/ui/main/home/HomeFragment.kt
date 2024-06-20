package com.capstone.afeed.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.FragmentHomeBinding
import com.capstone.afeed.ui.authentication.AuthenticationActivity
import com.capstone.afeed.ui.bottomsheet.ModalBottomSheet

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding
        get() = _binding ?: throw IllegalStateException(getString(R.string.illegal_state_exception))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        with(binding) {
            infoHomeSection.btnElevatedSignNowButton.setOnClickListener {
                val intent =
                    Intent(requireContext(), AuthenticationActivity::class.java)
                startActivity(intent)
            }
            btnAFeeding.setOnClickListener {
                ModalBottomSheet(1).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnCustomizeIot.setOnClickListener {
                ModalBottomSheet(2).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnPhMonitoring.setOnClickListener {
                ModalBottomSheet(3).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
            btnTemperatureMonitoring.setOnClickListener {
                ModalBottomSheet(4).show(parentFragmentManager, ModalBottomSheet.TAG)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}