package com.rquimbiulco.pokedex.domain.model

data class UserModel(
    val userId: Int,
    val email: String,
    val userMode: UserMode
)

enum class UserMode(val id: Int) {
    TRAINER(0),
    ADMIN(1)
}
