package com.capstone.afeed.ui.fishpondform.temperaturesystemform

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ActivityTemperatureSystemFormBinding
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel

class TemperatureSystemFormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTemperatureSystemFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTemperatureSystemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializePreviousInputToViewModel()
    }

    private fun initializePreviousInputToViewModel() {
        val fishpondData = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<FishpondIotRequest>(
                FishPondFormViewModel.FISHPONDDATA_EXTRAS,
                FishpondIotRequest::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<FishpondIotRequest>(FishPondFormViewModel.FISHPONDDATA_EXTRAS)
        }
        Log.i("datas",fishpondData.toString())
//        if (fishpondData != null) {
//            fishPondFormViewModel.saveFishPondData(fishpondData)
//        }

    }
}