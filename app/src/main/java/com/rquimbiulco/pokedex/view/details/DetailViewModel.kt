package com.rquimbiulco.pokedex.view.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.rquimbiulco.pokedex.domain.repository.FavoritePokemonRepository
import com.rquimbiulco.pokedex.domain.repository.PokemonRepository
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import com.rquimbiulco.pokedex.view.core.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository,
    private val favoriteRepository: FavoritePokemonRepository
) :
    BaseViewModel<DetailUiState, DetailAction, DetailEvent>(DetailUiState()) {

    private val pokemonId = savedStateHandle.toRoute<Detail>().id

    init {
        getPokemonDetail()
        observeFavorite()
    }

    override fun handleAction(action: DetailAction) {
        when (action) {
            is DetailAction.OnFavoriteClicked -> {
                updateFavoriteStatus(action.isFavorite)
            }

            DetailAction.OnBackButtonClicked -> {
                launch { sendEvent(DetailEvent.NavigateBack) }
            }
        }
    }

    private fun getPokemonDetail() {
        launch {
            val pokemonDetail = repository.getPokemonDetail(pokemonId)
            updateState { state -> state.copy(pokemonDetail = pokemonDetail) }
        }
    }

    private fun observeFavorite() {
        launch {
            favoriteRepository.isFavorite(pokemonId).collect { isFavorite ->
                updateState { state ->
                    state.copy(isFavorite = isFavorite)
                }
            }
        }
    }

    private fun updateFavoriteStatus(isFavorite: Boolean) {
        launch {
            val pokemon = uiState.value.pokemonDetail
            if (isFavorite) {
                favoriteRepository.removeFavorite(pokemonId)
            } else {
                favoriteRepository.addFavorite(pokemon)
            }
            updateState { state -> state.copy(isFavorite = isFavorite) }
        }
    }
}