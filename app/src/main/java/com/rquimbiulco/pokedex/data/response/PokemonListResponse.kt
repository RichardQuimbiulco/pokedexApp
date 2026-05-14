package com.rquimbiulco.pokedex.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponse(
    val results: List<PokemonResponse>
)

@Serializable
data class PokemonResponse(
    val name: String,
    val url: String
)

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val sprites: PokemonSpriteResponse
)

@Serializable
data class PokemonSpriteResponse(
    @SerialName("front_default")
    val pokemonImage: String
)