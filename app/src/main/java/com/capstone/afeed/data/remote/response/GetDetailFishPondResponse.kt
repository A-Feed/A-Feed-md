package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class GetDetailFishPondResponse(
    @SerializedName("data")
    val data: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("connectedDevice")
        val connectedDevice: ConnectedDevice,
        @SerializedName("fishpond")
        val fishpond: Fishpond,
        @SerializedName("fishpondScore")
        val fishpondScore: FishpondScore,
    )

    data class ConnectedDevice(
        @SerializedName("aFeedingDeviceTotal")
        val aFeedingDeviceTotal: String,
        @SerializedName("phDeviceTotal")
        val phDeviceTotal: String,
        @SerializedName("temperatureDeviceTotal")
        val temperatureDeviceTotal: String
    )

    data class Fishpond(
        @SerializedName("fishpondId")
        val fishpondId: String,
        @SerializedName("fishType")
        val fishType: String,
        @SerializedName("fishpondDescription")
        val fishpondDescription: String,
        @SerializedName("fishpondName")
        val fishpondName: String,
        @SerializedName("feedingProgress")
        val feedingProgress: String,
    )

    data class FishpondScore(
        @SerializedName("cause")
        val cause: String,
        @SerializedName("fishpondStatus")
        val fishpondStatus: String,
        @SerializedName("phTotal")
        val phTotal: String,
        @SerializedName("score")
        val score: String,
        @SerializedName("temperatureTotal")
        val temperatureTotal: String
    )
}