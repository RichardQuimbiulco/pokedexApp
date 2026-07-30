package com.rquimbiulco.pokedex.domain.model

import com.rquimbiulco.pokedex.data.datasource.local.database.entity.UserEntity

data class UserRegisterModel(
    val userId: Int = System.currentTimeMillis().hashCode(),
    val email: String,
    val password: String,
    val userMode: UserMode
)

fun UserRegisterModel.toUserEntity(): UserEntity {
    return UserEntity(
        id = userId,
        email = email,
        password = password,
        userType = when (userMode) {
            UserMode.TRAINER -> 0
            UserMode.ADMIN -> 1
        }
    )
}