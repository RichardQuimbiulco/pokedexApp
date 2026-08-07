package com.rquimbiulco.pokedex.data.mapper

import com.rquimbiulco.pokedex.data.datasource.local.database.entity.PokemonEntity
import com.rquimbiulco.pokedex.data.response.PokemonResponse
import com.rquimbiulco.pokedex.util.extractPokemonId
import com.rquimbiulco.pokedex.util.toPokemonImageUrl


fun PokemonResponse.toEntity(): PokemonEntity {
    val id = url.extractPokemonId()

    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = id.toPokemonImageUrl()
    )
}

fun List<PokemonResponse>.toEntities(): List<PokemonEntity> =
    map { it.toEntity() }