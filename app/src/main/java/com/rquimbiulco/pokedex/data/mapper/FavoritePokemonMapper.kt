package com.rquimbiulco.pokedex.data.mapper

import com.rquimbiulco.pokedex.data.datasource.local.database.entity.FavoritePokemonEntity
import com.rquimbiulco.pokedex.domain.model.PokemonDetailModel
import com.rquimbiulco.pokedex.domain.model.PokemonModel
import com.rquimbiulco.pokedex.view.details.PokemonType

fun PokemonDetailModel.toFavoriteEntity(): FavoritePokemonEntity {
    return FavoritePokemonEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        primaryType = types[0].id,
        secondaryType = types.getOrNull(1)?.id
    )
}

fun FavoritePokemonEntity.toModel(): PokemonModel {
    return PokemonModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        types = listOfNotNull(
            PokemonType.fromId(primaryType),
            secondaryType?.let { PokemonType.fromId(it) }
        )
    )
}