package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class StatusWithMessageResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)