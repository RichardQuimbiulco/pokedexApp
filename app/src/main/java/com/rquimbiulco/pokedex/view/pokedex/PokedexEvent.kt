package com.rquimbiulco.pokedex.view.pokedex

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

sealed interface PokedexUiEvent : ScreenEvent {
    data object NavigateToLogin : PokedexUiEvent
}