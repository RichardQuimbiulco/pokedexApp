package com.rquimbiulco.pokedex.view.auth.register

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

interface RegisterUserEvent: ScreenEvent {
    data object NavigateBack : RegisterUserEvent
    data object NavigateToPokedex : RegisterUserEvent
}