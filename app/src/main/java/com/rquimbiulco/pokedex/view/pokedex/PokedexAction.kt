package com.rquimbiulco.pokedex.view.pokedex

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenAction
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination

sealed interface PokedexUiAction: ScreenAction {
    data class DrawerItemClicked(
        val destination: DrawerDestination
    ) : PokedexUiAction

    data class PokemonClicked(
        val pokemonId: Int
    ) : PokedexUiAction
}