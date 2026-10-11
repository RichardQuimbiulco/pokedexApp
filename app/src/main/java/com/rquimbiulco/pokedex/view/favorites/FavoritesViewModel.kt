package com.rquimbiulco.pokedex.view.favorites

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.domain.repository.FavoritePokemonRepository
import com.rquimbiulco.pokedex.domain.usecase.GetPokemonUseCase
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(favoriteRepository: FavoritePokemonRepository) :
    BaseViewModel<FavoritesUiState, FavoritesAction, FavoritesEvent>(FavoritesUiState()) {

    val favoritePokemonFlow: Flow<PagingData<PokemonModel>> =
        favoriteRepository
            .getFavorites()
            .cachedIn(viewModelScope)

    override fun handleAction(action: FavoritesAction) {
        when (action) {
            is FavoritesAction.OnBackButtonClicked -> {
                launch { sendEvent(FavoritesEvent.NavigateBack) }
            }
        }
    }
}