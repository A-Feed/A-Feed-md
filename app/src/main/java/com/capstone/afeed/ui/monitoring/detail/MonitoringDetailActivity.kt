package com.capstone.afeed.ui.monitoring.detail

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.capstone.afeed.R
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.databinding.ActivityMonitoringDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class MonitoringDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMonitoringDetailBinding
    private val monitoringDetailViewModel: MonitoringDetailViewModel by viewModels {
        MonitoringDetailViewModelFactory.getInstance(this@MonitoringDetailActivity)
    }
    private val monitoringSystemFragmentAdapter: MonitoringSystemFragmentAdapter by lazy {
        MonitoringSystemFragmentAdapter(this@MonitoringDetailActivity)
    }
    private var fishpondExtras: GetListFishPondResponse.Pond? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMonitoringDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tabLayoutItemText = listOf(
            getString(R.string.a_feeding_system),
            getString(R.string.ph_system),
            getString(R.string.temperature_system)
        )

        setupGetExtrasDatas()
        setupObserver()
        setupView()
        setupAdapter(tabLayoutItemText)

    }

    private fun setupGetExtrasDatas() {
        fishpondExtras = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<GetListFishPondResponse.Pond>(
                FISHPOND_EXTRAS,
                GetListFishPondResponse.Pond::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<GetListFishPondResponse.Pond>(FISHPOND_EXTRAS)
        }
        fishpondExtras?.fishpondId?.toInt()
            ?.let { monitoringDetailViewModel.setFishPondId(fishPondId = it) }
    }

//    private fun setupOnbackPressed(onBackPressedDispatcher: OnBackPressedDispatcher) {
//        onBackPressedDispatcher.addCallback(this) {
//            this.handleOnBackPressed().apply {
//                if (binding.viewpagerMonitoingDetail.currentItem == 0) {
//                    finish()
//                } else {
//                    binding.viewpagerMonitoingDetail.currentItem -= 1
//                }
//            }
//        }
//    }

    private fun setupAdapter(tabLayoutItemText: List<String>) {
        TabLayoutMediator(
            binding.tabLayoutRegisteredSystem,
            binding.viewpagerMonitoingDetail
        ) { tab, position ->
            tab.text = tabLayoutItemText[position]
        }.attach()
    }

    private fun setupObserver() {
            monitoringDetailViewModel.getDetailFishPond(
                1
            ).observe(this@MonitoringDetailActivity) {
                when (it) {
                    is ResponseState.Error -> {

                    }

                    ResponseState.Loading -> {

                    }

                    is ResponseState.Success -> {
                        val (connectedDevice, fishpond, fishpondScore) = it.data.data
                        with(binding) {
//                            CARD FISHPOND SCORE & STATUS
                            textViewFishpondStatus.text = fishpondScore.fishpondStatus
                            textViewFishpondCause.text = fishpondScore.cause
                            textViewFishpondPhTotalScore.text = fishpondScore.phTotal
                            textViewFishpondTemperatureTotalScore.text =
                                fishpondScore.temperatureTotal
                            textViewScoreFishpond.text = fishpondScore.score

//                            CARD FISHPOND PROFILE
                            textViewFeedingProgress.text = fishpond.feedingProgress
                            textViewFishpondName.text = fishpond.fishpondName
                            textViewFishpondDescription.text = fishpond.fishpondDescription
//                             SECTION CONNECTED DEVICE
                            textViewTotalAfeeding.text = connectedDevice.aFeedingDeviceTotal
                            textViewTotalPhSystem.text = connectedDevice.phDeviceTotal
                            textViewTotalTemperatureSystem.text =
                                connectedDevice.temperatureDeviceTotal
                        }
                    }
                }

        }
    }

    private fun setupView() {
        with(binding) {
            viewpagerMonitoingDetail.adapter = monitoringSystemFragmentAdapter
        }
    }


    companion object {
        const val FISHPOND_EXTRAS = "fishpond_extras"
    }

}