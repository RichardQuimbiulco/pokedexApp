package com.rquimbiulco.pokedex.data.response

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val userId: Int,
    val name: String,
    val userType: Int
)
