package com.rquimbiulco.pokedex.domain.model

data class UserModel(
    val userId: Int,
    val email: String,
    val userMode: UserMode
)

sealed class UserMode(val userType: Int) {
    data object trainerUser : UserMode(0)
    data object adminUser : UserMode(1)
}
