package com.rquimbiulco.pokedex.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rquimbiulco.pokedex.data.datasource.database.dao.PokemonDao
import com.rquimbiulco.pokedex.data.datasource.database.dao.UserDao
import com.rquimbiulco.pokedex.data.datasource.database.entity.PokemonEntity
import com.rquimbiulco.pokedex.data.datasource.database.entity.UserEntity

@Database(entities = [UserEntity::class, PokemonEntity::class], version = 1)
abstract class PokeDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pokemonDao(): PokemonDao
}