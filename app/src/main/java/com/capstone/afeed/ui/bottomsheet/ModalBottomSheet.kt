package com.capstone.afeed.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.capstone.afeed.R
import com.capstone.afeed.databinding.ModalBottomSheetContentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModalBottomSheet(private val featureNumber: Int) : BottomSheetDialogFragment() {
    private var _binding: ModalBottomSheetContentBinding? = null
    private val binding
        get() = _binding ?: throw IllegalStateException(getString(R.string.illegal_state_exception))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ModalBottomSheetContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (featureNumber) {
            1 -> {
                with(binding) {
                    tvBottomSheetTitle.text = getString(R.string.a_feeding)
                    tvBottomSheetDescription.text = getString(R.string.a_feeding_bottom_sheet)
                }
            }

            2 -> {
                with(binding) {
                    tvBottomSheetTitle.text = getString(R.string.customize_iot)
                    tvBottomSheetDescription.text = getString(R.string.customize_iot_bottom_sheet)
                }
            }

            3 -> {
                with(binding) {
                    tvBottomSheetTitle.text = getString(R.string.ph_monitoring)
                    tvBottomSheetDescription.text = getString(R.string.ph_monitoring_bottom_sheet)
                }
            }

            4 -> {
                with(binding) {
                    tvBottomSheetTitle.text = getString(R.string.temperature_monitoring)
                    tvBottomSheetDescription.text =
                        getString(R.string.temperature_monitoring_bottom_sheet)
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}