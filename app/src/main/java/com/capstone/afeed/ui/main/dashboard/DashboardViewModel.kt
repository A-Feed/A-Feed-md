package com.capstone.afeed.ui.main.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.afeed.repository.FishPondRepository

class DashboardViewModel (
    private val fishPondRepository: FishPondRepository
) : ViewModel() {

    fun getListRegisteredFishpondWithSystemMeanScore(userId: Int) = liveData{
        emitSource(
            fishPondRepository.getListRegisteredFishpondWithSystemMeanScore(userId)
        )
    }


}