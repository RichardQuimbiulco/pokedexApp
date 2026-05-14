package com.rquimbiulco.pokedex.data.repository.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.rquimbiulco.pokedex.data.datasource.api.ApiService
import com.rquimbiulco.pokedex.data.datasource.database.PokeDatabase
import com.rquimbiulco.pokedex.data.datasource.database.entity.PokemonEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
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
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        // Si ya tenemos datos, el siguiente offset es el tamaño actual de la lista
                        state.config.pageSize * (state.pages.size)
                        // O más simple: contar los registros en DB
                    }
                }
            }
            // 2. Llamada a la API (Paginada)
            val response = api.getPagedPokemonList(offset = offset, limit = state.config.pageSize)
            // 3. Enriquecimiento (el "async" que vimos antes)
            val pokemonEntities = response.results.map { resource ->
                coroutineScope {
                    async {
                        val detail = api.getPokemonDetail(resource.url)
                        PokemonEntity(name = resource.name, imageUrl = detail.sprites.pokemonImage)
                    }
                }
            }.awaitAll()

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