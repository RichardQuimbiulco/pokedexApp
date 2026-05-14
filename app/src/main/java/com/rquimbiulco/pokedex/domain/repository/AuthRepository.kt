package com.rquimbiulco.pokedex.domain.repository

import com.rquimbiulco.pokedex.domain.model.LoginResult

interface AuthRepository {
    suspend fun login(user: String, password: String): LoginResult
}