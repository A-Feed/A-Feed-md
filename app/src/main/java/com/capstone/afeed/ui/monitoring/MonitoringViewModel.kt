package com.capstone.afeed.ui.monitoring

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.afeed.repository.FishPondRepository

class MonitoringViewModel  constructor(
   private val fishPondRepository: FishPondRepository
) : ViewModel() {

    fun getListFishPond() = liveData {
        emitSource(
            fishPondRepository.getListFishPond()
        )
    }




}