package com.rquimbiulco.pokedex.data.response

import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.util.extractPokemonId
import com.rquimbiulco.pokedex.util.toPokemonImageUrl
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
