package com.capstone.afeed.ui.monitoring

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.afeed.R
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.databinding.ActivityMonitoringBinding
import com.capstone.afeed.ui.monitoring.detail.MonitoringDetailActivity

class MonitoringActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMonitoringBinding
    private lateinit var registeredFishpondAdapter: RegisteredFishpondAdapter
    private val monitoringViewModel : MonitoringViewModel by viewModels { MonitoringViewModelFactory.getInstance(this@MonitoringActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMonitoringBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.includedToolbarMain.tollbarMain)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAdapter()
        setupObserver()
        setupRecylerView()
    }

    private fun setupObserver() {
        monitoringViewModel.getListFishPond().observe(this@MonitoringActivity){
            with(binding){
                when(it){
                    is ResponseState.Error -> {

                    }
                    ResponseState.Loading -> {

                    }
                    is ResponseState.Success -> {
                        textViewFishpondTotal.text = it.data.data.size.toString()
                        registeredFishpondAdapter.submitList(it.data.data)
                    }
                }
            }
        }
    }

    private fun setupAdapter() {
        registeredFishpondAdapter = RegisteredFishpondAdapter()
        registeredFishpondAdapter.addNewListener = object : RegisteredFishpondAdapter.AddNewListener{
            override fun onClickItem(item: GetListFishPondResponse.Pond) {
                val intent = Intent(this@MonitoringActivity,MonitoringDetailActivity::class.java)
                    .putExtra(MonitoringDetailActivity.FISHPOND_EXTRAS,item)
                startActivity(intent)

            }

        }
    }

    private fun setupRecylerView() {
        with(binding){
            recylerViewRegisteredFishpond.apply {
                adapter = registeredFishpondAdapter
                layoutManager = LinearLayoutManager(this@MonitoringActivity,LinearLayoutManager.VERTICAL,false)
            }
        }
    }

}