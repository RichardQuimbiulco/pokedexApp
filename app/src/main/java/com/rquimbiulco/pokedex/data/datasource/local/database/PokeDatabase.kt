package com.rquimbiulco.pokedex.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rquimbiulco.pokedex.data.datasource.local.database.dao.FavoritePokemonDao
import com.rquimbiulco.pokedex.data.datasource.local.database.dao.PokemonDao
import com.rquimbiulco.pokedex.data.datasource.local.database.dao.UserDao
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.FavoritePokemonEntity
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.PokemonEntity
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.UserEntity

@Database(entities = [UserEntity::class, PokemonEntity::class, FavoritePokemonEntity::class], version = 1)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pokemonDao(): PokemonDao
    abstract fun favoritePokemonDao(): FavoritePokemonDao
}