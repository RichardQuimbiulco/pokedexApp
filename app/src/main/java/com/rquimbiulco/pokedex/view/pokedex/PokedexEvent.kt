package com.rquimbiulco.pokedex.view.pokedex

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

sealed interface PokedexEvent : ScreenEvent {
    data object NavigateToLogin : PokedexEvent
    data class NavigateToDetail(val id: Int) : PokedexEvent
}