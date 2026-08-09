package com.rquimbiulco.pokedex.view.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.rquimbiulco.pokedex.domain.repository.PokemonRepository
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import com.rquimbiulco.pokedex.view.core.navigation.Detail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: PokemonRepository
) :
    BaseViewModel<DetailUiState, DetailAction, DetailEvent>(DetailUiState()) {

    private val pokemonId = savedStateHandle.toRoute<Detail>().id

    init {
        getPokemonDetail()
    }

    override fun handleAction(action: DetailAction) {
        when (action) {
            is DetailAction.OnFavoriteClicked -> {}
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
}