package com.rquimbiulco.pokedex.view.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rquimbiulco.pokedex.domain.model.LoginResult
import com.rquimbiulco.pokedex.domain.usecase.LoginUseCase
import com.rquimbiulco.pokedex.view.auth.Validator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val loginUseCase: LoginUseCase, val validator: Validator) :
    ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChange(email: String) {
        _uiState.update { state ->
            state.copy(email = email)
        }
        validateLogin()
    }

    fun onPasswordChange(newPassword: String) {
        _uiState.update { state ->
            state.copy(password = newPassword)
        }
        validateLogin()
    }

    fun onMenuItemSelected(userType: String, expanded: Boolean) {
        _uiState.update { state ->
            state.copy(userType = userType, expanded = expanded)
        }
    }

    fun onMenuItemExpanded(expanded: Boolean) {
        _uiState.update { state ->
            state.copy(expanded = expanded)
        }
    }

    fun onClickSelected() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(authStatus = AuthStatus.Loading) }
            when (val result = loginUseCase(_uiState.value.email, _uiState.value.password)) {
                is LoginResult.Success -> {
                    _uiState.update { it.copy(authStatus = AuthStatus.Success(result.userModel.email)) }
                }

                is LoginResult.InvalidCredentials -> {
                    _uiState.update { it.copy(authStatus = AuthStatus.Error("Credenciales inválidas")) }
                }

                is LoginResult.Error -> {
                    _uiState.update { it.copy(authStatus = AuthStatus.Error(result.error)) }
                }
            }
        }
    }

    fun onPasswordShowChange(show: Boolean) {
        _uiState.update { state ->
            state.copy(passwordVisibility = show)
        }
    }

    private fun validateLogin() {
        val email = _uiState.value.email
        val password = _uiState.value.password
        val isLoginEnabled =
            email.isNotBlank() && password.isNotBlank()
                    && !validator.isValidEmail(email) && !validator.isValidPassword(password)
        _uiState.update { state ->
            state.copy(isLoginEnabled = isLoginEnabled)
        }
    }

}
