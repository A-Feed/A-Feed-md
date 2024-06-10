package com.capstone.afeed.ui.fishpondform.phsystemform

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.databinding.ActivityAfeedingFormBinding
import com.capstone.afeed.databinding.ActivityPhSystemFormBinding
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.capstone.afeed.ui.fishpondform.temperaturesystemform.TemperatureSystemFormActivity

class PhSystemFormActivity : AppCompatActivity() {

    private lateinit var phSystemAdapter: PhSystemAdapter
    private lateinit var binding: ActivityPhSystemFormBinding
    private val fishPondFormViewModel: FishPondFormViewModel by viewModels {
        FishPondFormViewModelFactory.getInstance(
            this@PhSystemFormActivity
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPhSystemFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeAdapter()
        initializePreviousInputToViewModel()
        setupView()
        setupRecylerView()
    }


    private fun setupView() {
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
                textIndicatorView2.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView2.setImageResource(R.color.md_theme_primary)
                textIndicatorView3.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView3.setImageResource(R.color.md_theme_primary)
            }
            btnAddPhSystem.setOnClickListener {
                fishPondFormViewModel.addPhSystem(
                    FishpondIotRequest.Phsystem(phSystemAdapter.itemCount, "")
                )
            }
            fabNext.setOnClickListener {
                val intent = Intent(this@PhSystemFormActivity, TemperatureSystemFormActivity::class.java)
                    .putExtra(
                        FishPondFormViewModel.FISHPONDDATA_EXTRAS,
                        fishPondFormViewModel.fishpondData.value
                    )
                startActivity(intent)
            }
        }
    }

    private fun initializeAdapter() {
        phSystemAdapter = PhSystemAdapter()
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
                    this@PhSystemFormActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
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
        if (fishpondData != null) {
            fishPondFormViewModel.saveFishPondData(fishpondData)
        }
        Log.i("datasphsystemactivty",fishpondData.toString())

    }

}