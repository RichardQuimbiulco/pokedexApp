package com.rquimbiulco.pokedex.domain.repository

import com.rquimbiulco.pokedex.domain.model.UserModel
import com.rquimbiulco.pokedex.domain.model.UserRegisterModel
import com.rquimbiulco.pokedex.domain.model.LoginResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(userModel: UserRegisterModel): Result<Unit>

    suspend fun getUserByCredentials(email: String, password: String): LoginResult

}