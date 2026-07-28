package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState

data class RegisterUiState(
    val passwordVisibility: Boolean = false,
    val confirmPasswordVisibility: Boolean = false,
    val showPasswordMatchError: Boolean = false,
    val showEmailFormatError: Boolean = false,
    val isValidPassword: Boolean = false,
    val isSaveEnabled: Boolean = false,
    val registerForm: RegisterForm = RegisterForm(),
    val isLoading: Boolean = false
) : ScreenState

data class RegisterForm(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val userType: UserMode = UserMode.trainerUser,
)

sealed class FormEvent {
    data class EmailChanged(val email: String) : FormEvent()
    data class PasswordChanged(val pass: String) : FormEvent()
    data class ConfirmPasswordChanged(val confirmPass: String) : FormEvent()
}