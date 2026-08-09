package com.rquimbiulco.pokedex.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val order: Int,
    val sprites: PokemonSpriteResponse,
    val types: List<TypeResponse>,
    val weight: Double,
    val height: Double,
    @SerialName("base_experience")
    val experience: Int,
    @SerialName("stats")
    val stats: List<StatsResponse>,
    val abilities: List<AbilitiesResponse>
)

@Serializable
data class PokemonSpriteResponse(
    @SerialName("front_default")
    val pokemonImage: String
)
@Serializable
data class TypeResponse(
    val type: Type,
    val slot: Int
)
@Serializable
data class Type(
    val name: String,
    val url: String
)

@Serializable
data class StatsResponse(
    @SerialName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
)

@Serializable
data class Stat(
    val name: String,
    val url: String
)

@Serializable
data class AbilitiesResponse(
    val ability: Ability,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)

@Serializable
data class Ability(
    val name: String,
    val url: String
)