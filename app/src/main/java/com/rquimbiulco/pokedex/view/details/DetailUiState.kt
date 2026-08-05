package com.rquimbiulco.pokedex.view.details

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState

data class DetailUiState(
    val isFavorite: Boolean = false,
) : ScreenState
