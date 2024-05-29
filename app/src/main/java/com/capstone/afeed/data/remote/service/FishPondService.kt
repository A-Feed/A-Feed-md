package com.capstone.afeed.data.remote.service

import com.capstone.afeed.data.remote.response.GetDetailFishPondResponse
import com.capstone.afeed.data.remote.response.GetListAFeedingFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.data.remote.response.GetListPhSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListTemperatureSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetTotalRegisteredFishPondResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface FishPondService {

    @GET("/registered/iotdevice/{id}")
    suspend fun getTotalRegisteredFishpond(
        @Path("id") userId: Int
    ) : GetTotalRegisteredFishPondResponse

    @GET("/list/fishpond")
    suspend fun getListFishPond() : GetListFishPondResponse

    @GET("/fishpond/{id}")
    suspend fun getDetailFishPond(
        @Path("id") fishPondId: Int
    ) : GetDetailFishPondResponse

    @GET("/fishpond/{id}/list/afeeding")
    suspend fun getListAFeedingFromFishPondId(
        @Path("id") fishPondId: Int
    ) : GetListAFeedingFromFishPondIDResponse

    @GET("/fishpond/{id}/list/phsystem")
    suspend fun getListPhSystemFromFishPondID(
        @Path("id") fishPondId: Int
    ) : GetListPhSystemFromFishPondIDResponse

    @GET("/fishpond/{id}/list/tempraturesystem")
    suspend fun getListTemperatureSystemFromFishPondID(
        @Path("id") fishPondId: Int
    ) : GetListTemperatureSystemFromFishPondIDResponse

}