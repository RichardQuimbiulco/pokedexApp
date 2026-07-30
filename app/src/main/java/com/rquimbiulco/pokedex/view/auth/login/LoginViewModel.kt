package com.rquimbiulco.pokedex.view.auth.login

import com.rquimbiulco.pokedex.domain.model.LoginResult
import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.domain.usecase.LoginUseCase
import com.rquimbiulco.pokedex.view.auth.Validator
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase, val validator: Validator) :
    BaseViewModel<LoginUiState, LoginAction, LoginEvent>(initialState = LoginUiState()) {

    override fun handleAction(action: LoginAction) {
        when (action) {
            LoginAction.OnLoginClicked -> onClickSelected()
            LoginAction.OnRegisterClicked -> launch { sendEvent(LoginEvent.NavigateToRegister) }
            is LoginAction.OnEmailChanged -> onEmailChange(action.email)
            is LoginAction.OnPasswordChanged -> onPasswordChange(action.password)
            is LoginAction.OnPasswordShowChange -> onPasswordShowChange(action.show)
            is LoginAction.OnMenuItemSelected -> onMenuItemSelected(
                action.userMode,
                action.expanded
            )

            is LoginAction.OnMenuItemExpanded -> onMenuItemExpanded(action.expanded)
        }
    }

    private fun onEmailChange(email: String) {
        updateState { state ->
            state.copy(email = email)
        }
        validateLogin()
    }

    private fun onPasswordChange(newPassword: String) {
        updateState { state ->
            state.copy(password = newPassword)
        }
        validateLogin()
    }

    private fun onMenuItemSelected(userMode: UserMode, expanded: Boolean) {
        updateState { state ->
            state.copy(userType = userMode, expanded = expanded)
        }
    }

    private fun onMenuItemExpanded(expanded: Boolean) {
        updateState { state ->
            state.copy(expanded = expanded)
        }
    }

    private fun onPasswordShowChange(show: Boolean) {
        updateState { state ->
            state.copy(passwordVisibility = show)
        }
    }

    private fun onClickSelected() {
        launch {
            updateState { it.copy(isLoading = true) }
            when (loginUseCase(uiState.value.email, uiState.value.password)) {
                is LoginResult.Success -> {
                    sendEvent(LoginEvent.NavigateToPokedex)
                    updateState { it.copy(isLoading = false) }
                }

                is LoginResult.InvalidCredentials -> {
                    sendEvent(
                        LoginEvent.ShowError(
                            LoginError.InvalidCredentials
                        )
                    )
                    updateState { it.copy(isLoading = false) }
                }

                is LoginResult.Error -> {
                    sendEvent(
                        LoginEvent.ShowError(
                            LoginError.Unknown
                        )
                    )
                    updateState { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun validateLogin() {
        val email = uiState.value.email
        val password = uiState.value.password
        val isLoginEnabled =
            email.isNotBlank() && password.isNotBlank()
                    && !validator.isValidEmail(email) && !validator.isValidPassword(password)
        updateState { state ->
            state.copy(isLoginEnabled = isLoginEnabled)
        }
    }

}
