package com.capstone.afeed.data.remote.response

import com.google.gson.annotations.SerializedName


data class GetListFishPondResponse(
    @SerializedName("data")
    val `data`: List<Pond>,
    @SerializedName("status")
    val status: String
) {
    data class Pond(
        @SerializedName("fishpondId")
        val fishpondId: String,
        @SerializedName("fishpondName")
        val fishpondName: String,
        @SerializedName("fishpondDescription")
        val fishpondDescription: String,
    )
}
