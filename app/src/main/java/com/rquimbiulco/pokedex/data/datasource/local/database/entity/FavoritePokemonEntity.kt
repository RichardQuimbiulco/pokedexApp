package com.rquimbiulco.pokedex.data.datasource.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoritePokemonEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val imageUrl: String,
    val primaryType: Int,
    val secondaryType: Int?
)
