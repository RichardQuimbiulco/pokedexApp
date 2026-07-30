package com.rquimbiulco.pokedex.view.auth.login

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

interface LoginEvent : ScreenEvent {
    data object NavigateToRegister : LoginEvent
    data object NavigateToPokedex : LoginEvent
    data class ShowError(val error: LoginError) : LoginEvent
}