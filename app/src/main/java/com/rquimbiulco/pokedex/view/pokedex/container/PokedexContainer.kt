package com.rquimbiulco.pokedex.view.pokedex.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.rquimbiulco.pokedex.view.pokedex.PokeDexViewModel
import com.rquimbiulco.pokedex.view.pokedex.PokedexEvent
import com.rquimbiulco.pokedex.view.pokedex.PokedexScreen

@Composable
fun PokedexContainer(
    navigateToLogin: () -> Unit,
) {
    val viewModel: PokeDexViewModel = hiltViewModel()
    val lazyPokemonItems = viewModel.pokemonFlow.collectAsLazyPagingItems()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                PokedexEvent.NavigateToLogin -> {
                    navigateToLogin()
                }
            }
        }
    }

    PokedexScreen(
        onAction = viewModel::handleAction,
        pokemonItems = lazyPokemonItems
    )
}