package com.rquimbiulco.pokedex.data.repository

import com.rquimbiulco.pokedex.data.datasource.local.datastore.SessionDataStore
import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl  @Inject constructor(private val sessionDataStore: SessionDataStore) : SessionRepository {
    override suspend fun login() {
        sessionDataStore.setLoggedIn(true)
    }

    override suspend fun logout() {
        sessionDataStore.setLoggedIn(false)
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return sessionDataStore.isLoggedIn
    }
}