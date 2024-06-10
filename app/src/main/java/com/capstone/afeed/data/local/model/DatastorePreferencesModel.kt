package com.capstone.afeed.data.local.model


data class DatastorePreferencesModel(
    val token: String?,
    val user: User?
){
    data class User(
        val id: Int?,
        val token: String?,
        val darkMode: Int?,
        val language : String?
    )
}
