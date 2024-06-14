package com.capstone.afeed.ui.monitoring.detail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.afeed.di.FishPondInjection
import com.capstone.afeed.repository.FishPondRepository
import com.capstone.afeed.ui.monitoring.MonitoringViewModel
import com.capstone.afeed.ui.monitoring.MonitoringViewModelFactory

class MonitoringDetailViewModelFactory
private constructor(
    private val fishPondRepository: FishPondRepository
): ViewModelProvider.NewInstanceFactory(){


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonitoringDetailViewModel::class.java)) {
            return MonitoringDetailViewModel(fishPondRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: MonitoringDetailViewModelFactory? = null

        fun getInstance(context: Context): MonitoringDetailViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MonitoringDetailViewModelFactory(FishPondInjection.provideRepository(context))
            }.also { INSTANCE = it }
    }
}