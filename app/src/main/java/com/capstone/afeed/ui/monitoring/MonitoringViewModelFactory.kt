package com.capstone.afeed.ui.monitoring

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.afeed.di.FishPondInjection
import com.capstone.afeed.repository.FishPondRepository
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory

class MonitoringViewModelFactory
private constructor(
    private val fishPondRepository: FishPondRepository
): ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MonitoringViewModel::class.java)) {
            return MonitoringViewModel(fishPondRepository) as T
//            return FishPondFormViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: MonitoringViewModelFactory? = null

        fun getInstance(context: Context): MonitoringViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MonitoringViewModelFactory(FishPondInjection.provideRepository(context))
//                INSTANCE ?: FishPondFormViewModelFactory()
            }.also { INSTANCE = it }
    }
}