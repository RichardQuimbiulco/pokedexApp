package com.rquimbiulco.pokedex.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.rquimbiulco.pokedex.data.datasource.api.ApiService
import com.rquimbiulco.pokedex.data.datasource.local.database.PokeDatabase
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.toModel
import com.rquimbiulco.pokedex.data.mapper.toModel
import com.rquimbiulco.pokedex.data.repository.mediator.PokemonRemoteMediator
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val database: PokeDatabase
) : PokemonRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPokemonStream(): Flow<PagingData<PokemonModel>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = PokemonRemoteMediator(apiService, database),
            pagingSourceFactory = { database.pokemonDao().getPokemonPagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { entity -> entity.toModel() }
        }
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetailModel {
        return apiService.getPokemonDetailById(id).toModel()
    }
}