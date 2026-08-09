package com.rquimbiulco.pokedex.data.mapper

import com.rquimbiulco.pokedex.data.response.PokemonDetailResponse
import com.rquimbiulco.pokedex.domain.model.AbilityModel
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.StatModel

fun PokemonDetailResponse.toModel(): PokemonDetailModel =
    PokemonDetailModel(
        id = id,
        name = name,
        order = order,
        imageUrl = sprites.pokemonImage,
        types = types.map { it.type.name },
        weight = weight,
        height = height,
        experience = experience,
        stats = stats.map { StatModel(name = it.stat.name, baseStat = it.baseStat) },
        abilities = abilities.map { AbilityModel(name = it.ability.name, isHidden = it.isHidden) })