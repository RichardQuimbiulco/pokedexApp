package com.rquimbiulco.pokedex.view.favorites.container

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.rquimbiulco.pokedex.view.favorites.FavoritesEvent
import com.rquimbiulco.pokedex.view.favorites.FavoritesScreen
import com.rquimbiulco.pokedex.view.favorites.FavoritesViewModel

@Composable
fun FavoritesContainer(navigateBack: () -> Unit) {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val lazyPokemonItems = viewModel.favoritePokemonFlow.collectAsLazyPagingItems()

    LaunchedEffect(viewModel) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                FavoritesEvent.NavigateBack -> navigateBack()
            }
        }
    }

    FavoritesScreen(
        state = state,
        onAction = viewModel::onAction,
        pokemonItems = lazyPokemonItems
    )
}
