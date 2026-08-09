package com.rquimbiulco.pokedex.view.details

import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState

data class DetailUiState(
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val pokemonDetail: PokemonDetailModel = PokemonDetailModel()
) : ScreenState
