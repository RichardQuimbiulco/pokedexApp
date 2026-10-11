package com.rquimbiulco.pokedex.view.favorites

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction
import com.rquimbiulco.pokedex.view.pokedex.PokedexAction

interface FavoritesAction : ScreenAction {
    data object OnBackButtonClicked : FavoritesAction

    data class PokemonClicked(
        val pokemonId: Int
    ) : FavoritesAction
}