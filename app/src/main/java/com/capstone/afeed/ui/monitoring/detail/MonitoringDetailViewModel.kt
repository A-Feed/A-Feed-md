package com.capstone.afeed.ui.monitoring.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.afeed.repository.FishPondRepository

class MonitoringDetailViewModel constructor(
    private val fishPondRepository: FishPondRepository
) : ViewModel() {

    private var _fishPondId: Int? = null
    val fishPondId get() = _fishPondId!!

    fun setFishPondId(fishPondId: Int){
        _fishPondId = fishPondId

    }
    fun getDetailFishPond(fishPondId: Int) = liveData {
        emitSource(
            fishPondRepository.getDetailFishPond(fishPondId)
        )
    }

    fun getListAFeedingFromFishPondId(
        fishPondId: Int
    ) = liveData {
        emitSource(
            fishPondRepository.getListAFeedingFromFishPondId(fishPondId)
        )
    }

    fun getListPhSystemFromFishPondID(
        fishPondId: Int
    ) = liveData {
        emitSource(
            fishPondRepository.getListPhSystemFromFishPondID(fishPondId)
        )
    }

    fun getListTemperatureSystemFromFishPondID(
        fishPondId: Int
    ) = liveData {
        emitSource(
            fishPondRepository.getListTemperatureSystemFromFishPondID(fishPondId)
        )
    }

}