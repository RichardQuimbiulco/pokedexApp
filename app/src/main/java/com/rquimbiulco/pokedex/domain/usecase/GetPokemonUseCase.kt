package com.rquimbiulco.pokedex.domain.usecase

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModel() {
    operator fun invoke(): Flow<PagingData<PokemonModel>> = pokemonRepository.getPokemonStream()
}