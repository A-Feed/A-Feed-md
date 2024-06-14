package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class GetListRegisteredFishpondWithSystemMeanScore(
    @SerializedName("data")
    val `data`: List<FishpondWithMeanSystemData>,
    @SerializedName("status")
    val status: String
){

    data class FishpondWithMeanSystemData(
        @SerializedName("aFeedingProgress")
        val aFeedingProgress: String,
        @SerializedName("fishpondDescription")
        val fishpondDescription: String,
        @SerializedName("fishpondName")
        val fishpondName: String,
        @SerializedName("id_fishpond")
        val idFishpond: String,
        @SerializedName("phScoreTotal")
        val phScoreTotal: String,
        @SerializedName("temperatureScoreTotal")
        val temperatureScoreTotal: String
    )

}
