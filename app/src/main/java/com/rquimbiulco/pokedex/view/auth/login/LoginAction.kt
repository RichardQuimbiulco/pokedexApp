package com.rquimbiulco.pokedex.view.auth.login

import com.rquimbiulco.pokedex.domain.model.UserMode
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction

interface LoginAction : ScreenAction {
    data object OnLoginClicked : LoginAction
    data object OnRegisterClicked : LoginAction
    data class OnEmailChanged(val email: String) : LoginAction
    data class OnPasswordChanged(val password: String) : LoginAction
    data class OnPasswordShowChange(val show: Boolean) : LoginAction
    data class OnMenuItemSelected(val userMode: UserMode, val expanded: Boolean) : LoginAction
    data class OnMenuItemExpanded(val expanded: Boolean) : LoginAction
}