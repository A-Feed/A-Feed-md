package com.capstone.afeed.ui.main.dashboard

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capstone.afeed.di.FishPondInjection
import com.capstone.afeed.repository.FishPondRepository
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModel
import com.capstone.afeed.ui.fishpondform.FishPondFormViewModelFactory

class DashboardViewModelFactory
    private constructor(
        val fishPondRepository : FishPondRepository
    ) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FishPondFormViewModel::class.java)) {
            return FishPondFormViewModel(fishPondRepository) as T
//            return FishPondFormViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var INSTANCE: DashboardViewModelFactory? = null

        fun getInstance(context: Context): DashboardViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: DashboardViewModelFactory(FishPondInjection.provideRepository(context))
//                INSTANCE ?: FishPondFormViewModelFactory()
            }.also { INSTANCE = it }
    }
}