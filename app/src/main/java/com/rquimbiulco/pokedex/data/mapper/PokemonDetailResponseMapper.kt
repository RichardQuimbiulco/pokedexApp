package com.rquimbiulco.pokedex.data.mapper

import com.rquimbiulco.pokedex.data.response.PokemonDetailResponse
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel

fun PokemonDetailResponse.toModel(): PokemonDetailModel = PokemonDetailModel(id = id, name = name)