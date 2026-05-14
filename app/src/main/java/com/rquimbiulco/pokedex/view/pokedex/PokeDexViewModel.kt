package com.rquimbiulco.pokedex.view.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rquimbiulco.pokedex.domain.usecase.GetPokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokeDexViewModel @Inject constructor(
    getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {
    // cachedIn permite que la paginación sobreviva a rotaciones de pantalla
    val pokemonFlow = getPokemonUseCase().cachedIn(viewModelScope)
}