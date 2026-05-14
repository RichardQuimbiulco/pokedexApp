package com.rquimbiulco.pokedex.domain.model

sealed class LoginResult {
    object InvalidCredentials : LoginResult()
    data class Success(val userModel: UserModel) : LoginResult()
    data class Error(val error: String) : LoginResult()
}