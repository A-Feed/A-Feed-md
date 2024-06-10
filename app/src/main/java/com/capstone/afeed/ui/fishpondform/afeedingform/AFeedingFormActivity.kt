package com.capstone.afeed.ui.fishpondform.afeedingform

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
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory
import com.capstone.afeed.ui.fishpondform.fishpondprofileform.FishPondFormActivity
import com.capstone.afeed.ui.fishpondform.phsystemform.PhSystemFormActivity
import kotlin.random.Random

class AFeedingFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAfeedingFormBinding
    private val fishPondFormViewModel: FishPondFormViewModel by viewModels {
        FishPondFormViewModelFactory.getInstance(
            this@AFeedingFormActivity
        )
    }
    private lateinit var aFeedingScheduleAdapter: AFeedingScheduleAdapter
    private lateinit var aFeedingSystemAdapter: AFeedingSystemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAfeedingFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeAdapter()
        initializePreviousInputToViewModel()
        setupAdapter()
        setupObserver()
        setupView()
        setupRecylerView()
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

    }

    private fun setupAdapter() {
        aFeedingScheduleAdapter.addNewListener = object : AFeedingScheduleAdapter.AddNewListener {
            override fun inputDataListener(
                dataInput: FishpondIotRequest.AFeedingSchedule,
                id: Int
            ) {
                fishPondFormViewModel.editInputDataAFeedingSchedule(id, dataInput)
            }
        }
        aFeedingSystemAdapter.addNewListener = object : AFeedingSystemAdapter.AddNewListener {
            override fun insertItemListener(
                dataInput: FishpondIotRequest.AFeedingSystem,
                id: Int
            ) {
                fishPondFormViewModel.editInputDataAFeedingSystem(id, dataInput)
            }

            override fun deleteItemListener(id: Int) {
                fishPondFormViewModel.deleteInputDataAFeedingSystem(id)
            }
        }
    }

    private fun initializeAdapter() {
        aFeedingScheduleAdapter = AFeedingScheduleAdapter()
        aFeedingSystemAdapter = AFeedingSystemAdapter()
    }

    private fun setupView() {
        with(binding) {
            includeProgressFormIndicator.apply {
                textIndicatorView1.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView1.setImageResource(R.color.md_theme_primary)
                textIndicatorView2.setTextColor(getColor(R.color.md_theme_onPrimary))
                circleImageIndicatorView2.setImageResource(R.color.md_theme_primary)
            }
            btnAddAFeedingSchedule.setOnClickListener {
                addAfeedingInput()
            }
            btnAddAFeedingSystem.setOnClickListener {
                addAfeedingSystem()
            }
            fabNext.setOnClickListener {
                val intent = Intent(this@AFeedingFormActivity, PhSystemFormActivity::class.java)
                    .putExtra(
                        FishPondFormViewModel.FISHPONDDATA_EXTRAS,
                        fishPondFormViewModel.fishpondData.value
                    )
                startActivity(intent)
            }
        }
    }

    private fun setupObserver() {
        with(fishPondFormViewModel) {
            fishpondAFeedingSchedule.observe(this@AFeedingFormActivity) {
                aFeedingScheduleAdapter.submitList(it.toList())
            }
            fishpondAFeedingSystem.observe(this@AFeedingFormActivity) {
                aFeedingSystemAdapter.submitList(it.toList())
                Log.i("datasjumlahatas",it.toString())
                Log.i("datasjumlahatas",aFeedingSystemAdapter.itemCount.toString())

            }
            fishpondData.observe(this@AFeedingFormActivity) {
                Log.i("datas", it.toString())
                aFeedingSystemAdapter.submitList(it.afeeding?.aFeedingSystem?.toList())
                Log.i("datasjumlah",aFeedingSystemAdapter.itemCount.toString())
            }
        }

    }

    private fun setupRecylerView() {
        with(binding) {
            recylerViewAFeedingSchedule.apply {
                layoutManager = LinearLayoutManager(
                    this@AFeedingFormActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = aFeedingScheduleAdapter
            }
            recylerViewAFeedingSystem.apply {
                layoutManager = LinearLayoutManager(
                    this@AFeedingFormActivity,
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


}