package com.capstone.afeed.repository

import androidx.lifecycle.liveData
import com.capstone.afeed.data.ResponseState
import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.data.remote.response.ErrorResponse
import com.capstone.afeed.data.remote.response.GetDetailFishPondResponse
import com.capstone.afeed.data.remote.response.GetListAFeedingFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.data.remote.response.GetListPhSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListTemperatureSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetTotalRegisteredFishPondResponse
import com.capstone.afeed.data.remote.service.FishPondService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import retrofit2.http.GET
import retrofit2.http.Path

class FishPondRepository
private constructor(private val fishPondService: FishPondService)
{

    suspend fun getTotalRegisteredFishpond(
        userId: Int
    ) = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getTotalRegisteredFishpond(userId)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
        }
    }

    suspend fun getListFishPond() = liveData {
        emit(ResponseState.Loading)
        try {
            val result = fishPondService.getListFishPond()
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
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
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
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
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
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
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
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
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
        }
    }

    fun postRegisterFishPondCreate(fishpondIotRequest: FishpondIotRequest) = liveData(Dispatchers.IO) {
        try {
            val result = fishPondService.postRegisterFishPondCreate(fishpondIotRequest)
            emit(ResponseState.Success(result))
        } catch (e: HttpException) {
            val errorMessage = Gson().fromJson<ErrorResponse>(
                e.response()?.errorBody()?.string(),
                ErrorResponse::class.java
            )
            emit(ResponseState.Error(errorMessage.message))
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