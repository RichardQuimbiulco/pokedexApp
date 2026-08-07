package com.rquimbiulco.pokedex.data.repository

import com.rquimbiulco.pokedex.domain.repository.AuthRepository
import com.rquimbiulco.pokedex.domain.repository.UserRepository
import com.rquimbiulco.pokedex.domain.model.LoginResult
import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val userRepository: UserRepository, private val sessionRepository: SessionRepository) :
    AuthRepository {
    override suspend fun login(user: String, password: String): LoginResult {
        return withContext(Dispatchers.IO) {
            try {
                return@withContext userRepository.getUserByCredentials(user, password)
            } catch (e: Exception) {
                LoginResult.Error("Error al acceder a la base de datos: ${e.message}")
            }
        }
    }
}