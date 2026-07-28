package com.rquimbiulco.pokedex.view.pokedex

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rquimbiulco.pokedex.domain.repository.SessionRepository
import com.rquimbiulco.pokedex.domain.usecase.GetPokemonUseCase
import com.rquimbiulco.pokedex.view.core.architecture.base.BaseViewModel
import com.rquimbiulco.pokedex.view.core.navigation.DrawerDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokeDexViewModel @Inject constructor(
    getPokemonUseCase: GetPokemonUseCase,
    private val sessionRepository: SessionRepository
) : BaseViewModel<PokedexState, PokedexAction, PokedexEvent>(initialState = PokedexState()) {
    // cachedIn permite que la paginación sobreviva a rotaciones de pantalla
    val pokemonFlow = getPokemonUseCase().cachedIn(viewModelScope)

    override fun handleAction(action: PokedexAction) {
        when (action) {
            is PokedexAction.DrawerItemClicked -> viewModelScope.launch { selectDrawer(action.destination) }
            is PokedexAction.PokemonClicked -> {}
        }
    }

    private suspend fun selectDrawer(
        destination: DrawerDestination
    ) {
        when (destination) {
            DrawerDestination.Logout -> {
                sessionRepository.logout()
                sendEvent(
                    PokedexEvent.NavigateToLogin
                )
            }

            DrawerDestination.Home -> {}
            DrawerDestination.Settings -> {}
            DrawerDestination.Favorites -> {}
        }

    }
}