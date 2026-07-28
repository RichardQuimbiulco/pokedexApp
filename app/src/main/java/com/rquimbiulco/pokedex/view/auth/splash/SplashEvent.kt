package com.rquimbiulco.pokedex.view.auth.splash

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

sealed interface SplashUiEvent: ScreenEvent {
    data object NavigateToLogin: SplashUiEvent
    data object NavigateToPokedex: SplashUiEvent
}