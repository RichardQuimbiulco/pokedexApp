package com.rquimbiulco.pokedex.domain.model

import com.rquimbiulco.pokedex.util.Constants.Companion.EMPTY_STRING
import com.rquimbiulco.pokedex.util.toPokemonImageUrl
import com.rquimbiulco.pokedex.view.details.PokemonType

data class PokemonDetailModel(
    val id: Int = 0,
    val order: Int = 0,
    val name: String = EMPTY_STRING,
    val imageUrl: String = id.toPokemonImageUrl(),
    val types: List<PokemonType> = emptyList(),
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val experience: Int = 0,
    val stats: List<StatModel> = emptyList(),
    val abilities: List<AbilityModel> = emptyList()
)

data class StatModel(
    val name: String,
    val baseStat: Int
)

data class AbilityModel(
    val name: String,
    val isHidden: Boolean
)

