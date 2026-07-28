package com.rquimbiulco.pokedex.data.datasource.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import javax.inject.Inject

class SessionDataStore @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }

    val isLoggedIn: Flow<Boolean> =
        dataStore.data.map { preferences ->
            preferences[IS_LOGGED_IN] ?: false
        }

    private companion object {
        val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
    }
}