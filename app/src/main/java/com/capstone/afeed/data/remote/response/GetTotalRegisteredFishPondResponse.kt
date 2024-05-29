package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class GetTotalRegisteredFishPondResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
){
    data class Data(
        @SerializedName("totalAFeedingSystem")
        val totalAFeedingSystem: String,
        @SerializedName("totalPhSystem")
        val totalPhSystem: String,
        @SerializedName("totalTemperatureSystem")
        val totalTemperatureSystem: String,
        @SerializedName("tottalIot")
        val tottalIot: String
    )
}
