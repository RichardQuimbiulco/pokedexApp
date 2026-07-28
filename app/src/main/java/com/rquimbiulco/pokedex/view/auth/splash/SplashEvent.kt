package com.rquimbiulco.pokedex.view.auth.splash

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

sealed interface SplashEvent: ScreenEvent {
    data object NavigateToLogin: SplashEvent
    data object NavigateToPokedex: SplashEvent
}