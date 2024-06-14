package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class GetListPhSystemFromFishPondIDResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
){
    data class Data(
        @SerializedName("id_ph_system")
        val idPhSystem: String,
        @SerializedName("idTopicMqtt")
        val idTopicMqtt: String,
        @SerializedName("ph_score")
        val phScore: String,
        @SerializedName("status")
        val status: String
    )
}
