package com.capstone.afeed.data.remote.response
import com.google.gson.annotations.SerializedName


data class NotificationResponse(
    @SerializedName("message_header")
    val messageHeader: String,
    @SerializedName("messege_body")
    val messegeBody: String,
    @SerializedName("status")
    val status: String
)