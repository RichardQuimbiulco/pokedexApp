package com.rquimbiulco.pokedex.data.datasource.datastore

import kotlinx.coroutines.flow.Flow

interface SessionDataStore {
    suspend fun loggedIn()
    suspend fun loggedOut()
    fun isLoggedIn(): Flow<Boolean>
}