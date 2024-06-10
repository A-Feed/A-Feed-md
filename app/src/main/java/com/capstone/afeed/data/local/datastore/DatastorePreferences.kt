package com.capstone.afeed.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.capstone.afeed.data.local.model.DatastorePreferencesModel
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.datastore : DataStore<Preferences> by preferencesDataStore("user")

class DatastorePreferences private constructor(private val datastore: DataStore<Preferences>){

    fun getSession(): Flow<DatastorePreferencesModel?> {
        val session = datastore.data.map { preferences ->
            val data =  Gson().fromJson(preferences[SESSION] ?: "", DatastorePreferencesModel::class.java)
            data
        }
        return session
    }

    suspend fun saveSession(session: DatastorePreferencesModel) {
        datastore.edit { preferences ->
            preferences[SESSION] = Gson().toJson(session)
        }
    }

    suspend fun clearSession() {
        datastore.edit { preferences ->
            preferences.remove(SESSION)
        }
    }

    companion object {
        private val SESSION = stringPreferencesKey("user_session")

        @Volatile
        private var INSTANCE: DatastorePreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): DatastorePreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = DatastorePreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}