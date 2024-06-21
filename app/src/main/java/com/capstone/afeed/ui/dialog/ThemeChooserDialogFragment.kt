package com.capstone.afeed.ui.dialog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.capstone.afeed.R
import com.capstone.afeed.databinding.DialogSuccessFragmentBinding
import com.capstone.afeed.databinding.FragmentThemeChooserDialogBinding

class ThemeChooserDialogFragment : DialogFragment() {
    private var _binding : FragmentThemeChooserDialogBinding? = null
    private val binding get() = _binding!!

    override fun onResume() {
        super.onResume()
        val getDisplayDimension = resources.displayMetrics
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        this.dialog?.window?.setLayout((getDisplayDimension.widthPixels * 9) /10, WindowManager.LayoutParams.WRAP_CONTENT)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentThemeChooserDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupView()
    }

    private val themeList = listOf(
        "dark",
        "light"
    )

    private var choosedTheme = "light"

    private fun setupView() {
        with(binding){
            radioDarkTheme.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    choosedTheme = themeList[0]
                }
            }
            radioLightTheme.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked){
                    choosedTheme = themeList[1]
                }
            }
            btnSave.setOnClickListener {
                when(choosedTheme){
                    "light" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                    "dark" -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
            }

        }
    }

    companion object{
       const val TAG = "ThemeChooserDialogFragment"
    }

}