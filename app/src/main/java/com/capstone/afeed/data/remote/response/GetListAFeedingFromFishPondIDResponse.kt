package com.capstone.afeed.data.remote.response

import com.google.gson.annotations.SerializedName


data class GetListAFeedingFromFishPondIDResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
) {
    data class Data(
        @SerializedName("listAFeedingSystem")
        val listAFeedingSystem: List<AFeedingSystem>,
        @SerializedName("listSchedule")
        val listSchedule: List<Schedule>
    )

    data class AFeedingSystem(
        @SerializedName("feedingProgress")
        val feedingProgress: String,
        @SerializedName("idTopicMqtt")
        val idTopicMqtt: String,
        @SerializedName("id_a_feeding")
        val idAFeeding: String,
        @SerializedName("status")
        val status: String
    )

    data class Schedule(
        @SerializedName("foodAmount")
        val foodAmount: String,
        @SerializedName("id_a_feeding_schedule")
        val idAFeedingSchedule: String,
        @SerializedName("time")
        val time: String
    )

}