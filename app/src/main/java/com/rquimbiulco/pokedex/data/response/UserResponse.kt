package com.rquimbiulco.pokedex.data.response

import com.rquimbiulco.pokedex.domain.model.UserModel
import com.rquimbiulco.pokedex.domain.model.UserMode
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val userId: Int,
    val name: String,
    val userType: Int
)

fun UserResponse.toDomain(): UserModel {

    val userMode = when (userType) {
        UserMode.TRAINER.id -> UserMode.TRAINER
        UserMode.ADMIN.id -> UserMode.ADMIN
        else -> UserMode.TRAINER
    }
    return UserModel(
        userId = userId,
        email = name,
        userMode = userMode
    )
}