package com.rquimbiulco.pokedex.data.datasource.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.domain.model.UserModel

@Entity
data class UserEntity(
    @PrimaryKey
    val id: Int,
    val email: String,
    val password: String,
    val userType: Int
)

fun UserEntity.toUserModel(): UserModel {
    return UserModel(
        userId = id,
        email = email,
        userMode = when (userType) {
            0 -> UserMode.trainerUser
            1 -> UserMode.adminUser
            else -> UserMode.trainerUser
        }
    )
}