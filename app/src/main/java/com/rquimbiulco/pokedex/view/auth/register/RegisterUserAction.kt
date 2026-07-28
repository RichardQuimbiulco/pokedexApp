package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction

interface RegisterUserAction : ScreenAction {
    data object OnSaveButtonClicked : RegisterUserAction
    data object OnBackButtonClicked : RegisterUserAction
    data class OnEmailChanged(val email: String) : RegisterUserAction
    data class OnPasswordChanged(val password: String) : RegisterUserAction
    data class OnConfirmPasswordChanged(val confirmPassword: String) : RegisterUserAction
    data class OnPasswordShowChange(val show: Boolean) : RegisterUserAction
    data class OnConfirmPasswordShowChange(val show: Boolean): RegisterUserAction
    data class OnUserTypeChanged(val userType: String) : RegisterUserAction
}