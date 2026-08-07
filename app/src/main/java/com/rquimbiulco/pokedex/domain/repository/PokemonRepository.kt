package com.rquimbiulco.pokedex.domain.repository

import androidx.paging.PagingData
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonStream(): Flow<PagingData<PokemonModel>>
    suspend fun getPokemonDetail(id: Int): PokemonDetailModel
}