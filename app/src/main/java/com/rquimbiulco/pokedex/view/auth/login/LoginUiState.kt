package com.rquimbiulco.pokedex.view.auth.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = false,
    val userType: String = "Normal",
    val isLoading: Boolean = false,
    val isLoginEnabled: Boolean = false,
    val expanded: Boolean = false,
    val authStatus: AuthStatus = AuthStatus.Idle
)
sealed class AuthStatus {
    object Idle : AuthStatus()
    object Loading : AuthStatus()
    data class Success(val userName: String) : AuthStatus()
    data class Error(val message: String) : AuthStatus()
}
