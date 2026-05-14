package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.domain.model.UserMode

data class RegisterUiState(
    val passwordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
    val showPasswordMatchError: Boolean = false,
    val showEmailFormatError: Boolean = false,
    val isValidPassword: Boolean = false,
    val isSaveEnabled: Boolean = false,
    val registerForm: RegisterForm = RegisterForm(),
    val registrationStatus: RegisterUserUiState = RegisterUserUiState.Idle
)

data class RegisterForm(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val userType: UserMode = UserMode.trainerUser,
)

sealed interface RegisterUserUiState {
    object Idle : RegisterUserUiState
    object Loading : RegisterUserUiState
    object Success : RegisterUserUiState
    data class Error(val message: String) : RegisterUserUiState
}

sealed class FormEvent {
    data class EmailChanged(val email: String) : FormEvent()
    data class PasswordChanged(val pass: String) : FormEvent()
    data class ConfirmPasswordChanged(val confirmPass: String) : FormEvent()
}