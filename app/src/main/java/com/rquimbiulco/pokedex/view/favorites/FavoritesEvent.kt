package com.rquimbiulco.pokedex.view.favorites

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenEvent

interface FavoritesEvent : ScreenEvent {
    data object NavigateBack : FavoritesEvent
}