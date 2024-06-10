package com.capstone.afeed.ui.fishpondform

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.repository.FishPondRepository

class FishPondFormViewModel(
    private val fishPondRepository: FishPondRepository
) : ViewModel() {

    private val _fispondData = MutableLiveData<FishpondIotRequest>()
    val fishpondData: LiveData<FishpondIotRequest> = _fispondData

    private val _fishpondProfile = MutableLiveData<FishpondIotRequest.Fishpond>()
    val fishpondProfile: LiveData<FishpondIotRequest.Fishpond> = _fishpondProfile

    private val _fishpondAFeedingSchedule =
        MutableLiveData<ArrayList<FishpondIotRequest.AFeedingSchedule>>()
    val fishpondAFeedingSchedule: LiveData<ArrayList<FishpondIotRequest.AFeedingSchedule>> =
        _fishpondAFeedingSchedule

    private val _fishpondAFeedingSystem =
        MutableLiveData<ArrayList<FishpondIotRequest.AFeedingSystem>>()
    val fishpondAFeedingSystem: LiveData<ArrayList<FishpondIotRequest.AFeedingSystem>> =
        _fishpondAFeedingSystem

    private val _fishpondPhsystem = MutableLiveData<ArrayList<FishpondIotRequest.Phsystem>>()
    val fishpondPhsystem: LiveData<ArrayList<FishpondIotRequest.Phsystem>> = _fishpondPhsystem

    private val _fishpondTemperaturesystem =
        MutableLiveData<ArrayList<FishpondIotRequest.Temperaturesystem>>()
    val fishpondTemperaturesystem: LiveData<ArrayList<FishpondIotRequest.Temperaturesystem>> =
        _fishpondTemperaturesystem

    fun saveFishPondData(data: FishpondIotRequest) {
        _fispondData.postValue(data)
    }

    fun saveFishpondProfile(datas: FishpondIotRequest.Fishpond) {
        _fishpondProfile.postValue(datas)
        val updateFishpondData = _fispondData.value
        _fispondData.postValue(
            FishpondIotRequest(
                fishpond = datas ?: FishpondIotRequest.Fishpond("", ""),
                temperaturesystem = updateFishpondData?.temperaturesystem,
                afeeding = updateFishpondData?.afeeding,
                phsystem = updateFishpondData?.phsystem
            )
        )
    }

    //    FISHPOND SCHEDULE
    fun addAFeedingSchedule(data: FishpondIotRequest.AFeedingSchedule) {
        val updateList = _fishpondAFeedingSchedule.value ?: ArrayList()
        updateList.add(data)
        _fishpondAFeedingSchedule.postValue(updateList)

    }

    fun editInputDataAFeedingSchedule(position: Int, newData: FishpondIotRequest.AFeedingSchedule) {
        val updateList = _fishpondAFeedingSchedule.value ?: ArrayList()
        val data = updateList.map {
            if (it.id == position) {
                newData
            } else {
                it
            }
        }.toCollection(ArrayList())
        _fishpondAFeedingSchedule.postValue(data)
        val updateListAFeedingSystem = _fishpondAFeedingSystem.value ?: ArrayList()
        addAFeedingScheduletoFishPondData(data.toList(), updateListAFeedingSystem.toList())
    }

    fun deleteInputDataAFeedingSchedule(id: Int) {
        val updateList = _fishpondAFeedingSchedule.value ?: ArrayList()
        updateList.removeIf { it.id == id }
        val updateListAFeedingSystem = _fishpondAFeedingSystem.value ?: ArrayList()
        addAFeedingScheduletoFishPondData(updateList.toList(), updateListAFeedingSystem.toList())
    }

    private fun addAFeedingScheduletoFishPondData(
        aFeedingScehdule: List<FishpondIotRequest.AFeedingSchedule>,
        aFeedingSystem: List<FishpondIotRequest.AFeedingSystem>
    ) {
        val updateFishpondData = _fispondData.value
        if (updateFishpondData != null) {
            _fispondData.postValue(
                FishpondIotRequest(
                    fishpond = updateFishpondData.fishpond,
                    temperaturesystem = updateFishpondData.temperaturesystem,
                    afeeding = FishpondIotRequest.Afeeding(aFeedingScehdule, aFeedingSystem),
                    phsystem = updateFishpondData.phsystem
                )
            )
        }
    }

//    FISHPOND SCHEDULE

    //    FISHPOND AFEEDING SYSTEM
    fun addAFeedingSystem(data: FishpondIotRequest.AFeedingSystem) {
        val updateList = _fishpondAFeedingSystem.value ?: ArrayList()
        updateList.add(data)
        _fishpondAFeedingSystem.postValue(updateList)
    }

    fun editInputDataAFeedingSystem(position: Int, newData: FishpondIotRequest.AFeedingSystem) {
        val updateList = _fishpondAFeedingSystem.value ?: ArrayList()
        val data = updateList.map {
            if (it.id == position) {
                newData
            } else {
                it
            }
        }.toCollection(ArrayList())
        _fishpondAFeedingSystem.postValue(data)
        val updateListAFeedingSchedule = _fishpondAFeedingSchedule.value ?: ArrayList()
        addAFeedingSystemtoFishPondData(data.toList(), updateListAFeedingSchedule.toList())
    }

    fun deleteInputDataAFeedingSystem(id: Int) {
        val updateList = _fishpondAFeedingSystem.value ?: ArrayList()
        updateList.removeIf {
            it.id == id
        }
        _fishpondAFeedingSystem.postValue(updateList)
        val updateListAFeedingSchedule = _fishpondAFeedingSchedule.value ?: ArrayList()
        addAFeedingSystemtoFishPondData(updateList.toList(), updateListAFeedingSchedule.toList())
    }

    private fun addAFeedingSystemtoFishPondData(
        aFeedingSystem: List<FishpondIotRequest.AFeedingSystem>,
        aFeedingScehdule: List<FishpondIotRequest.AFeedingSchedule>
    ) {
        val updateFishpondData = _fispondData.value
        if (updateFishpondData != null) {
            _fispondData.postValue(
                FishpondIotRequest(
                    fishpond = updateFishpondData.fishpond,
                    temperaturesystem = updateFishpondData.temperaturesystem,
                    afeeding = FishpondIotRequest.Afeeding(aFeedingScehdule, aFeedingSystem),
                    phsystem = updateFishpondData.phsystem
                )
            )
        }
    }
//    FISHPOND AFEEDING SYSTEM

    //    FISHPOND PH SYSTEM
    fun addPhSystem(data: FishpondIotRequest.Phsystem) {
        val updateList = _fishpondPhsystem.value ?: ArrayList()
        updateList.add(data)
        _fishpondPhsystem.postValue(updateList)
    }

    fun editInputDataPhSystem(position: Int, newData: FishpondIotRequest.Phsystem) {
        val updateList = _fishpondPhsystem.value ?: ArrayList()
        val data = updateList.map {
            if (it.id == position) {
                newData
            } else {
                it
            }
        }.toCollection(ArrayList())
        _fishpondPhsystem.postValue(data)
        addPhSystemtoFishPondData(data.toList())
    }

    fun deleteInputDataPhSystem(id: Int) {
        val updateList = _fishpondPhsystem.value ?: ArrayList()
        updateList.removeIf { it.id == id }
        _fishpondPhsystem.postValue(updateList)
        addPhSystemtoFishPondData(updateList.toList())
    }

    private fun addPhSystemtoFishPondData(
        phSystem: List<FishpondIotRequest.Phsystem>
    ) {
        val updateFishpondData = _fispondData.value
        if (updateFishpondData != null) {
            _fispondData.postValue(
                FishpondIotRequest(
                    fishpond = updateFishpondData.fishpond,
                    temperaturesystem = updateFishpondData.temperaturesystem,
                    afeeding = updateFishpondData.afeeding,
                    phsystem = phSystem
                )
            )
        }
    }
//    FISHPOND PH SYSTEM

    //    FISHPOND TEMPERATURE SYSTEM
    fun addTemperatureSystem(data: FishpondIotRequest.Temperaturesystem) {
        val updateList = _fishpondTemperaturesystem.value ?: ArrayList()
        updateList.add(data)
        _fishpondTemperaturesystem.postValue(updateList)
    }

    fun editInputDataTemperatureSystem(position: Int, newData: FishpondIotRequest.Temperaturesystem) {
        val updateList = _fishpondTemperaturesystem.value ?: ArrayList()
        val data = updateList.map {
            if (it.id == position) {
                newData
            } else {
                it
            }
        }.toCollection(ArrayList())
        _fishpondTemperaturesystem.postValue(data)
        addTemperatureSystemtoFishPondData(data.toList())
    }

    fun deleteInputDataTemperatureSystem(id: Int) {
        val updateList = _fishpondTemperaturesystem.value ?: ArrayList()
        updateList.removeIf { it.id == id }
        _fishpondTemperaturesystem.postValue(updateList)
        addTemperatureSystemtoFishPondData(updateList.toList())
    }

    private fun addTemperatureSystemtoFishPondData(
        temperatureSystem: List<FishpondIotRequest.Temperaturesystem>
    ) {
        val updateFishpondData = _fispondData.value
        if (updateFishpondData != null) {
            _fispondData.postValue(
                FishpondIotRequest(
                    fishpond = updateFishpondData.fishpond,
                    temperaturesystem = temperatureSystem,
                    afeeding = updateFishpondData.afeeding,
                    phsystem = updateFishpondData.phsystem
                )
            )
        }
    }
//    FISHPOND TEMPERATURE SYSTEM

//    fun savePhsystem(datas: List<FishpondIotRequest.Phsystem>) {
//        _fishpondPhsystem.postValue(datas)
//    }
//
//    fun saveTemperaturesystem(datas: List<FishpondIotRequest.Temperaturesystem>) {
//        _fishpondTemperaturesystem.postValue(datas)
//    }

    fun postRegisterFishPondCreate(fishpondIotRequest: FishpondIotRequest) =
        liveData {
            emitSource(
                fishPondRepository.postRegisterFishPondCreate(fishpondIotRequest)
            )

        }

    companion object {
        val FISHPONDDATA_EXTRAS = "fishponddata_extra"
    }

}