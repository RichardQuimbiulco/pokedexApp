package com.rquimbiulco.pokedex.data.repository

import com.rquimbiulco.pokedex.data.datasource.local.database.dao.UserDao
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.toUserModel
import com.rquimbiulco.pokedex.domain.model.UserRegisterModel
import com.rquimbiulco.pokedex.domain.model.toUserEntity
import com.rquimbiulco.pokedex.domain.repository.UserRepository
import com.rquimbiulco.pokedex.domain.model.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userDao: UserDao) : UserRepository {

    override suspend fun insertUser(userModel: UserRegisterModel): Result<Unit> =
        runCatching { userDao.insertUser(userModel.toUserEntity()) }

    override suspend fun getUserByCredentials(
        email: String,
        password: String
    ): LoginResult {
        return withContext(Dispatchers.IO) {
            try {
                val userEntity = userDao.getUserByCredentials(email, password)
                if (userEntity != null) {
                    LoginResult.Success(userEntity.toUserModel())
                } else {
                    LoginResult.InvalidCredentials
                }
            } catch (e: Exception) {
                LoginResult.Error("Error al acceder a la base de datos: ${e.message}")
            }
        }
    }

}