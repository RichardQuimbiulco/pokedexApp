package com.rquimbiulco.pokedex.domain.repository

import androidx.paging.PagingData
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import kotlinx.coroutines.flow.Flow

interface FavoritePokemonRepository {
    suspend fun addFavorite(pokemon: PokemonDetailModel)

    suspend fun removeFavorite(id: Int)

    fun getFavorites(): Flow<PagingData<PokemonModel>>

    fun isFavorite(id: Int): Flow<Boolean>
}