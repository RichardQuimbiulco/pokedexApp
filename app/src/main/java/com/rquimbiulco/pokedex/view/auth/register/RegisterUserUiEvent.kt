package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

interface RegisterUserUiEvent: ScreenEvent {
    data object NavigateBack : RegisterUserUiEvent
    data object NavigateToPokedex : RegisterUserUiEvent
    data class ShowError(val message: String) : RegisterUserUiEvent
}