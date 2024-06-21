package com.capstone.afeed.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.capstone.afeed.databinding.DialogErrorFragmentBinding

class ErrorDialogFragment : DialogFragment() {

    private var _binding: DialogErrorFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val getDisplayDimension = resources.displayMetrics
        this.dialog?.window?.setLayout(
            (getDisplayDimension.widthPixels * 9) / 10,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogErrorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            btnCloseErrorDialog.setOnClickListener {
                dialog?.dismiss()
            }
            textViewErrorMessageDescription.text = arguments?.getString(MESSAGE_DESCRIPTION)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        const val MESSAGE_DESCRIPTION = "message_description"
        const val TAG = "ErrorDialog"
    }

}