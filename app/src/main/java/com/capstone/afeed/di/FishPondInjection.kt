package com.capstone.afeed.di

import android.content.Context
import com.capstone.afeed.data.local.datastore.DatastorePreferences
import com.capstone.afeed.data.local.datastore.datastore
import com.capstone.afeed.data.remote.config.FishPondApiConfig
import com.capstone.afeed.repository.FishPondRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object FishPondInjection {

    fun provideRepository(context: Context): FishPondRepository {
        val datastorePreferences = DatastorePreferences.getInstance(context.datastore)
//        val userPreferencesData = runBlocking { datastorePreferences.getSession().first() }
//        val fishPondService = FishPondApiConfig.getService(userPreferencesData?.token ?: "")
        val fishPondService = FishPondApiConfig.getService("")
        return FishPondRepository.getInstance(fishPondService)
    }
}