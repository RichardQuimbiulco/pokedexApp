package com.rquimbiulco.pokedex.data.datasource.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getAllUsers(): Flow<List<UserEntity>>

    // Busca el usuario por credenciales. Devuelve null si no existe.
    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByCredentials(email: String, password: String): UserEntity?
}