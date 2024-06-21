package com.capstone.afeed.data.remote.service

import com.capstone.afeed.data.remote.request.FishpondIotRequest
import com.capstone.afeed.data.remote.response.GetDetailFishPondResponse
import com.capstone.afeed.data.remote.response.GetListAFeedingFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListFishPondResponse
import com.capstone.afeed.data.remote.response.GetListPhSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetListRegisteredFishpondWithSystemMeanScore
import com.capstone.afeed.data.remote.response.GetListTemperatureSystemFromFishPondIDResponse
import com.capstone.afeed.data.remote.response.GetTotalRegisteredFishPondResponse
import com.capstone.afeed.data.remote.response.StatusWithMessageResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface FishPondService {

    @GET("/registered/iotdevice/{id}")
    suspend fun getTotalRegisteredFishpond(
        @Path("id") userId: Int
    ): GetTotalRegisteredFishPondResponse

    @GET("/registered/iotdevice/mean-score/{id}")
    suspend fun getListRegisteredFishpondWithSystemMeanScore(
        @Path("id") userId: Int
    ) : GetListRegisteredFishpondWithSystemMeanScore

    @GET("/list/fishpond")
    suspend fun getListFishPond(): GetListFishPondResponse

    @GET("/fishpond/{id}")
    suspend fun getDetailFishPond(
        @Path("id") fishPondId: Int
    ): GetDetailFishPondResponse

    @GET("/fishpond/{id}/list/afeeding")
    suspend fun getListAFeedingFromFishPondId(
        @Path("id") fishPondId: Int
    ): GetListAFeedingFromFishPondIDResponse

    @GET("/fishpond/{id}/list/phsystem")
    suspend fun getListPhSystemFromFishPondID(
        @Path("id") fishPondId: Int
    ): GetListPhSystemFromFishPondIDResponse

    @GET("/fishpond/{id}/list/temperaturesystem")
    suspend fun getListTemperatureSystemFromFishPondID(
        @Path("id") fishPondId: Int
    ): GetListTemperatureSystemFromFishPondIDResponse

    @POST("/register-fishpond/create")
    suspend fun postRegisterFishPondCreate(
        @Body fishpondIotRequest: FishpondIotRequest
    ) : StatusWithMessageResponse

    @PUT("/register-fishpond/update/{fishpondId}")
    suspend fun putRegisteredFishPond(
        @Path("fishpondId") fishPondId: Int,
        @Body fishpondIotRequest: FishpondIotRequest
    ) : StatusWithMessageResponse

    @DELETE("/register-iot/delete/{fishpondId}")
    suspend fun postRegisterFishPondDelete(
        @Path("fishpondId") fishPondId: Int,
        ) : StatusWithMessageResponse


}