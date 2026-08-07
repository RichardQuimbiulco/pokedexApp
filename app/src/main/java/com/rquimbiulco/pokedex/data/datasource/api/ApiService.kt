package com.rquimbiulco.pokedex.data.datasource.api

import com.rquimbiulco.pokedex.data.response.PokemonDetailResponse
import com.rquimbiulco.pokedex.data.response.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPagedPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetailById(@Path("id") id: Int): PokemonDetailResponse

}