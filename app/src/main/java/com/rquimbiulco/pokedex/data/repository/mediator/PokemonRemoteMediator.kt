package com.rquimbiulco.pokedex.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rquimbiulco.pokedex.data.datasource.api.ApiService
import com.rquimbiulco.pokedex.data.datasource.local.database.PokeDatabase
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.PokemonEntity
import com.rquimbiulco.pokedex.data.mapper.toEntities
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator @Inject constructor(
    private val api: ApiService,
    private val database: PokeDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            // 1. Calcular el offset
            val offset = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    database.pokemonDao().getCount()
                }
            }
            // 2. Llamada a la API (Paginada)
            val response = api.getPagedPokemonList(offset = offset, limit = state.config.pageSize)
            // 3. Creación del listado de PokemonEntity
            val pokemonEntities = response.results.toEntities()

            // 4. Guardar en Room
            database.withTransaction {
                if (loadType == LoadType.REFRESH) database.pokemonDao().clearAll()
                database.pokemonDao().insertAll(pokemonEntities)
            }

            MediatorResult.Success(endOfPaginationReached = response.results.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}