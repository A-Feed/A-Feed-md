package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class GetListTemperatureSystemFromFishPondIDResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
){
data class Data(
    @SerializedName("id_temp_system")
    val idTempSystem: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("temperatureScore")
    val temperatureScore: String
)
}