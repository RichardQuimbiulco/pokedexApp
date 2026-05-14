package com.rquimbiulco.pokedex.data.datasource.api

import com.rquimbiulco.pokedex.data.response.PokemonDetailResponse
import com.rquimbiulco.pokedex.data.response.PokemonListResponse
import com.rquimbiulco.pokedex.data.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {

    @GET("pokemon/")
    suspend fun getPagedPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): PokemonListResponse

    @GET
    suspend fun getPokemonDetail(@Url url: String): PokemonDetailResponse

}