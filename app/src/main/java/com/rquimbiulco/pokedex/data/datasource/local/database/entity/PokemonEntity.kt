package com.rquimbiulco.pokedex.data.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rquimbiulco.pokedex.domain.model.PokemonModel

@Entity
data class PokemonEntity(
    @PrimaryKey
    val id: Int = System.currentTimeMillis().hashCode(),
    val name: String,
    val imageUrl: String
)

fun PokemonEntity.toModel() = PokemonModel(
    id = id,
    name = name,
    imageUrl = imageUrl
)