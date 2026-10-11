package com.rquimbiulco.pokedex.domain.model

import com.rquimbiulco.pokedex.view.details.PokemonType

data class PokemonModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<PokemonType>
)