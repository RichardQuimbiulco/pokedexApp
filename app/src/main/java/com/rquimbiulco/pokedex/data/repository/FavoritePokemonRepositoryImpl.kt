package com.rquimbiulco.pokedex.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rquimbiulco.pokedex.data.datasource.local.database.PokeDatabase
import com.rquimbiulco.pokedex.data.mapper.toFavoriteEntity
import com.rquimbiulco.pokedex.data.mapper.toModel
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.domain.repository.FavoritePokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritePokemonRepositoryImpl @Inject constructor(
    private val database: PokeDatabase
) : FavoritePokemonRepository {
    override suspend fun addFavorite(pokemon: PokemonDetailModel) {
        database.favoritePokemonDao().insertFavorite(pokemon.toFavoriteEntity())
    }

    override suspend fun removeFavorite(id: Int) {
        database.favoritePokemonDao().deleteFavorite(id)
    }

    override fun getFavorites(): Flow<PagingData<PokemonModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                database.favoritePokemonDao().getFavoritesPagingSource()
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toModel()
            }
        }
    }

    override fun isFavorite(id: Int): Flow<Boolean> {
        return database.favoritePokemonDao().isFavorite(id)
    }
}