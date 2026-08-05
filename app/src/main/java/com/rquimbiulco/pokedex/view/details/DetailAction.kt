package com.rquimbiulco.pokedex.view.details

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction

interface DetailAction : ScreenAction {
    data class OnFavoriteClicked(val isFavorite: Boolean) : DetailAction
    data object OnBackButtonClicked : DetailAction
}