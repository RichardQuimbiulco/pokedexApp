package com.rquimbiulco.pokedex.domain.repository

import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun login()

    suspend fun logout()

    fun isLoggedIn(): Flow<Boolean>
}