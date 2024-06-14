package com.capstone.afeed.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class GetListFishPondResponse(
    @SerializedName("data")
    val data: List<Pond>,
    @SerializedName("status")
    val status: String
) {
    @Parcelize
    data class Pond(
        @SerializedName("fishpondId")
        val fishpondId: String,
        @SerializedName("fishpondName")
        val fishpondName: String,
        @SerializedName("fishpondDescription")
        val fishpondDescription: String,
    ) : Parcelable
}
