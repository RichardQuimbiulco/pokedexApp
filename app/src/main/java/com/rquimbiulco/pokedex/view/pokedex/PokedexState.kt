package com.rquimbiulco.pokedex.view.pokedex

import com.rquimbiulco.pokedex.view.core.architecture.state.ScreenState
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination

data class PokedexState(
    val selectedDrawerItem: DrawerDestination = DrawerDestination.Home
): ScreenState