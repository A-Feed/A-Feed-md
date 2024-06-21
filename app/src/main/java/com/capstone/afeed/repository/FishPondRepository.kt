package com.capstone.afeed.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.data.remote.response.ErrorResponse
import com.capstone.afeed.data.remote.service.FishPondService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class FishPondRepository
private constructor(private val fishPondService: FishPondService) {

    suspend fun getTotalRegisteredFishpond(
        userId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getTotalRegisteredFishpond(userId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    suspend fun getListRegisteredFishpondWithSystemMeanScore(
        userId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListRegisteredFishpondWithSystemMeanScore(userId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    suspend fun getListFishPond() = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListFishPond()
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    suspend fun getDetailFishPond(
        fishPondId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getDetailFishPond(fishPondId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    suspend fun getListAFeedingFromFishPondId(
        fishPondId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListAFeedingFromFishPondId(fishPondId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    suspend fun getListPhSystemFromFishPondID(
        fishPondId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListPhSystemFromFishPondID(fishPondId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    fun getListTemperatureSystemFromFishPondID(
        fishPondId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListTemperatureSystemFromFishPondID(fishPondId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    fun postRegisterFishPondCreate(fishpondIotRequest: FishpondIotRequest) =
        liveData {
            try {
                val result = fishPondService.postRegisterFishPondCreate(fishpondIotRequest)
                emit(ResponseState.Success(result))
            } catch (e: HttpException) {
                try {
                    val errorMessage = Gson().fromJson<ErrorResponse>(
                        e.response()?.errorBody()?.string(),
                        ErrorResponse::class.java
                    )
                    emit(ResponseState.Error(errorMessage.message))
                } catch (e: Exception) {
                    emit(ResponseState.Error("Unknown Error"))
                }
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        }

    suspend fun putRegisteredFishPond(fishPondId: Int,fishpondIotRequest: FishpondIotRequest) =
        liveData {
            try {
                val result = fishPondService.putRegisteredFishPond(fishPondId,fishpondIotRequest)
                emit(ResponseState.Success(result))
            } catch (e: HttpException) {
                try {
                    val errorMessage = Gson().fromJson<ErrorResponse>(
                        e.response()?.errorBody()?.string(),
                        ErrorResponse::class.java
                    )
                    emit(ResponseState.Error(errorMessage.message))
                } catch (e: Exception) {
                    emit(ResponseState.Error("Unknown Error"))
                }
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        }
    suspend fun postRegisterFishPondDelete(fishPondId: Int) =
        liveData {
            try {
                val result = fishPondService.postRegisterFishPondDelete(fishPondId)
                emit(ResponseState.Success(result))
            } catch (e: HttpException) {
                try {
                    val errorMessage = Gson().fromJson<ErrorResponse>(
                        e.response()?.errorBody()?.string(),
                        ErrorResponse::class.java
                    )
                    emit(ResponseState.Error(errorMessage.message))
                } catch (e: Exception) {
                    emit(ResponseState.Error("Unknown Error"))
                }
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        }

    fun getAllFishpondDataSystem(fishPondId: Int) = liveData {
        try {
            val resultDetail = fishPondService.getDetailFishPond(fishPondId)
            val resultListTemperature =
                fishPondService.getListTemperatureSystemFromFishPondID(fishPondId)
            val resultPhsystem = fishPondService.getListPhSystemFromFishPondID(fishPondId)
            val resultAFeeding = fishPondService.getListAFeedingFromFishPondId(fishPondId)


            emit(
                ResponseState.Success(
                    FishpondIotRequest(
                        fishpond = FishpondIotRequest.Fishpond(
                            resultDetail.data.fishpond.fishpondDescription.toString(),
                            resultDetail.data.fishpond.fishpondName.toString(),
                            resultDetail.data.fishpond.fishType.toString()
                        ),
                        afeeding = FishpondIotRequest.Afeeding(
                            aFeedingSystem = resultAFeeding.data.listAFeedingSystem.map {
                                FishpondIotRequest.AFeedingSystem(
                                    it.idAFeeding.toInt(),
                                    it.idTopicMqtt
                                )
                            },
                            aFeedingSchedule = resultAFeeding.data.listSchedule.map {
                                FishpondIotRequest.AFeedingSchedule(
                                    it.idAFeedingSchedule.toInt(),
                                    it.foodAmount,
                                    it.time
                                )
                            }
                        ),
                        temperaturesystem = resultListTemperature.data.map {
                            FishpondIotRequest.Temperaturesystem(
                                it.idTempSystem.toInt(),
                                it.idTopicMqtt
                            )
                        },
                        phsystem = resultPhsystem.data.map {
                            FishpondIotRequest.Phsystem(it.idPhSystem.toInt(), it.idTopicMqtt)
                        }
                    )

                )
            )

        } catch (e: HttpException) {
            try {
                val errorMessage = Gson().fromJson<ErrorResponse>(
                    e.response()?.errorBody()?.string(),
                    ErrorResponse::class.java
                )
                emit(ResponseState.Error(errorMessage.message))
            } catch (e: Exception) {
                emit(ResponseState.Error("Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error("Unknown Error"))
        }
    }

    companion object {
        private var INSTANCE: FishPondRepository? = null

        fun getInstance(fishPondService: FishPondService): FishPondRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FishPondRepository(fishPondService).also { INSTANCE = it }
            }
        }
    }

}