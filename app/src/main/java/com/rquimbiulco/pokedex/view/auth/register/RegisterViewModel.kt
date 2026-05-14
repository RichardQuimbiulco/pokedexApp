package com.rquimbiulco.pokedex.view.auth.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.domain.model.UserRegisterModel
import com.rquimbiulco.pokedex.domain.usecase.AddNewUserUseCase
import com.rquimbiulco.pokedex.view.auth.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validator: Validator,
    private val addNewUserUseCase: AddNewUserUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(RegisterUiState())
    private val events = MutableSharedFlow<FormEvent>()

    init {
        viewModelScope.launch {
            events
                .debounce(DEBOUNCE) // Espera 500ms de silencio y no mostrar inmediatamente el error de formato de email, para evitar mostrar el error mientras el usuario está escribiendo
                .collect { event ->
                    when (event) {
                        is FormEvent.EmailChanged -> {
                            uiState.update { state ->
                                state.copy(
                                    showEmailFormatError = validator.isValidEmail(event.email),
                                )
                            }.also { validateSaveUser() }
                        }

                        is FormEvent.PasswordChanged -> {
                            uiState.update { state ->
                                state.copy(
                                    isValidPassword = validator.isValidPassword(event.pass),
                                    showPasswordMatchError = validator.validatePasswordMatch(
                                        event.pass,
                                        uiState.value.registerForm.confirmPassword
                                    )
                                )
                            }.also { validateSaveUser() }
                        }

                        is FormEvent.ConfirmPasswordChanged -> {
                            uiState.update { state ->
                                state.copy(
                                    showPasswordMatchError = validator.validatePasswordMatch(
                                        uiState.value.registerForm.password,
                                        event.confirmPass
                                    )
                                )
                            }.also { validateSaveUser() }
                        }
                    }
                }
        }
    }

    fun onEmailChange(email: String) {
        uiState.update { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(email = email),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.EmailChanged(email))
        }
    }

    fun onPasswordChange(password: String) {
        uiState.update { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(password = password),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.PasswordChanged(password))
        }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        uiState.update { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(confirmPassword = confirmPassword),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.ConfirmPasswordChanged(confirmPassword))
        }
    }

    fun onUserTypeChange(userType: String) {
        uiState.update { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(userType = if (userType == TRAINER_USER) UserMode.trainerUser else UserMode.adminUser),
            )
        }
    }

    fun onPasswordShowChange(show: Boolean) {
        uiState.update { state ->
            state.copy(passwordVisibility = show)
        }
    }

    fun onConfirmPasswordShowChange(show: Boolean) {
        uiState.update { state ->
            state.copy(
                confirmPasswordVisibility = show
            )
        }
    }

    fun addNewUser() {
        viewModelScope.launch {
            uiState.value.registerForm.apply {
                val result = addNewUserUseCase(
                    UserRegisterModel(
                        email = this.email,
                        password = this.password,
                        userMode = this.userType
                    )
                )
                result.onSuccess {
                    uiState.update { state ->
                        state.copy(
                            registrationStatus = RegisterUserUiState.Success
                        )
                    }
                }.onFailure { error ->
                    uiState.update { state ->
                        state.copy(
                            registrationStatus = RegisterUserUiState.Error(
                                error.message ?: "Error desconocido"
                            )
                        )
                    }
                }
            }
        }
    }

    private fun validateSaveUser() {
        uiState.value.apply {
            val email = registerForm.email
            val password = registerForm.password
            val confirmPassword = registerForm.confirmPassword
            val isSaveEnabled =
                email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank() && !showEmailFormatError && !showPasswordMatchError && !isValidPassword
            uiState.update { state ->
                state.copy(isSaveEnabled = isSaveEnabled)
            }
        }
    }

    companion object {
        private const val TRAINER_USER = "Trainer"
        private const val DEBOUNCE = 500L
    }
}
