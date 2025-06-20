package com.example.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// PreferenceStorage.kt
// DataStore to save onboarding shown state
object PreferenceStorage {
    private val Context.dataStore by preferencesDataStore(name = "app_prefs")

    val ONBOARDING_SHOWN = booleanPreferencesKey("onboarding_shown")

    suspend fun setOnboardingShown(context: Context, shown: Boolean) {
        context.dataStore.edit { it[ONBOARDING_SHOWN] = shown }
    }

    fun isOnboardingShown(context: Context): Flow<Boolean> =
        context.dataStore.data.map { it[ONBOARDING_SHOWN] ?: false }
}
