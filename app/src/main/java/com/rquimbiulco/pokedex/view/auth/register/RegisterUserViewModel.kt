package com.rquimbiulco.pokedex.view.auth.register

import androidx.lifecycle.viewModelScope
import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.domain.model.UserRegisterModel
import com.rquimbiulco.pokedex.domain.usecase.AddNewUserUseCase
import com.rquimbiulco.pokedex.view.auth.Validator
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val validator: Validator,
    private val addNewUserUseCase: AddNewUserUseCase,
) : BaseViewModel<RegisterUiState, RegisterUserAction, RegisterUserUiEvent>(initialState = RegisterUiState()) {
    private val events = MutableSharedFlow<FormEvent>()

    override fun handleAction(action: RegisterUserAction) {
        when (action) {
            RegisterUserAction.OnSaveButtonClicked -> addNewUser()
            RegisterUserAction.OnBackButtonClicked -> launch { sendEvent(RegisterUserUiEvent.NavigateBack) }
            is RegisterUserAction.OnEmailChanged -> onEmailChange(action.email)
            is RegisterUserAction.OnPasswordChanged -> onPasswordChange(action.password)
            is RegisterUserAction.OnConfirmPasswordChanged -> onConfirmPasswordChange(action.confirmPassword)
            is RegisterUserAction.OnPasswordShowChange -> onPasswordShowChange(action.show)
            is RegisterUserAction.OnConfirmPasswordShowChange -> onConfirmPasswordShowChange(action.show)
            is RegisterUserAction.OnUserTypeChanged -> onUserTypeChange(action.userType)
        }
    }

    init {
        launch {
            events
                .debounce(DEBOUNCE) // Espera 500ms de silencio y no mostrar inmediatamente el error de formato de email, para evitar mostrar el error mientras el usuario está escribiendo
                .collect { event ->
                    when (event) {
                        is FormEvent.EmailChanged -> {
                            updateState { state ->
                                state.copy(
                                    showEmailFormatError = validator.isValidEmail(event.email),
                                )
                            }.also { validateSaveUser() }
                        }

                        is FormEvent.PasswordChanged -> {
                            updateState { state ->
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
                            updateState { state ->
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

    private fun onEmailChange(email: String) {
        updateState { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(email = email),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.EmailChanged(email))
        }
    }

    private fun onPasswordChange(password: String) {
        updateState { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(password = password),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.PasswordChanged(password))
        }
    }

    private fun onConfirmPasswordChange(confirmPassword: String) {
        updateState { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(confirmPassword = confirmPassword),
            )
        }
        viewModelScope.launch {
            events.emit(FormEvent.ConfirmPasswordChanged(confirmPassword))
        }
    }

    private fun onUserTypeChange(userType: String) {
        updateState { state ->
            state.copy(
                registerForm = uiState.value.registerForm.copy(userType = if (userType == TRAINER_USER) UserMode.trainerUser else UserMode.adminUser),
            )
        }
    }

    private fun onPasswordShowChange(show: Boolean) {
        updateState { state ->
            state.copy(passwordVisibility = show)
        }
    }

    private fun onConfirmPasswordShowChange(show: Boolean) {
        updateState { state ->
            state.copy(
                confirmPasswordVisibility = show
            )
        }
    }

    private fun addNewUser() {
        updateState { state -> state.copy(isLoading = true) }
        launch {
            uiState.value.registerForm.apply {
                val result = addNewUserUseCase(
                    UserRegisterModel(
                        email = this.email,
                        password = this.password,
                        userMode = this.userType
                    )
                )
                result.onSuccess {
                    sendEvent(
                        RegisterUserUiEvent.NavigateToPokedex
                    )
                    updateState { state -> state.copy(isLoading = false) }
                }.onFailure { error ->
                    sendEvent(
                        RegisterUserUiEvent.ShowError(
                            error.message ?: UNKNOWN_ERROR
                        )
                    )
                    updateState { state ->
                        state.copy(
                            isLoading = false,
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
            updateState { state ->
                state.copy(isSaveEnabled = isSaveEnabled)
            }
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "Error desconocido"
        private const val TRAINER_USER = "Trainer"
        private const val DEBOUNCE = 500L
    }
}
