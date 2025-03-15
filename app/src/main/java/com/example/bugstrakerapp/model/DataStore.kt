package com.example.bugstrakerapp.model

import androidx.datastore.preferences.core.Preferences
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "token")

class DataStoreManeger(val context: Context) {

    suspend fun tokenSet(token: String) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey("token")] = token
        }
    }

    suspend fun tokenGet() = context.dataStore.data.map { pref ->
        pref[stringPreferencesKey("token")]
    }
}