package com.rquimbiulco.pokedex.data.repository

import android.util.Log
import com.rquimbiulco.pokedex.data.datasource.api.ApiService
import com.rquimbiulco.pokedex.data.response.toDomain
import com.rquimbiulco.pokedex.domain.model.UserModel
import com.rquimbiulco.pokedex.domain.repository.AuthRepository
import com.rquimbiulco.pokedex.domain.repository.UserRepository
import com.rquimbiulco.pokedex.domain.model.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.collections.listOf

class AuthRepositoryImpl @Inject constructor(private val userRepository: UserRepository) :
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