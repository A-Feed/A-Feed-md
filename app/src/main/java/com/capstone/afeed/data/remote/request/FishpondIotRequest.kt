package com.capstone.afeed.data.remote.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FishpondIotRequest(
    val afeeding: Afeeding?,
    val fishpond: Fishpond?,
    val phsystem: List<Phsystem>?,
    val temperaturesystem: List<Temperaturesystem>?
) : Parcelable
{
    @Parcelize

    data class Afeeding(
        val aFeedingSchedule: List<AFeedingSchedule>,
        val aFeedingSystem: List<AFeedingSystem>
    ) : Parcelable

    @Parcelize
    data class Fishpond(
        val fishpondDescription: String,
        val fishpondName: String,
        val fishType : String
    ) : Parcelable

    @Parcelize
    data class Phsystem(
        val id: Int,
        val idTopicMqtt: String
    ) : Parcelable

    @Parcelize
    data class Temperaturesystem(
        val id: Int,
        val idTopicMqtt: String
    ) : Parcelable

    @Parcelize
    data class AFeedingSchedule(
        val id: Int,
        val foodAmount: String,
        val time: String
    ) : Parcelable

    @Parcelize
    data class AFeedingSystem(
        val id : Int,
        val idTopicMqtt: String
    ) : Parcelable

}
