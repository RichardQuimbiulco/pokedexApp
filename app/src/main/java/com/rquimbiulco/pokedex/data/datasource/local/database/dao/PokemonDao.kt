package com.rquimbiulco.pokedex.data.datasource.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.PokemonEntity

@Dao
interface PokemonDao {
    @Insert
    suspend fun insertPokemon(pokemon: PokemonEntity)

    @Query("SELECT * FROM PokemonEntity")
    fun getPokemonPagingSource(): PagingSource<Int, PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemon: List<PokemonEntity>)

    @Query("DELETE FROM PokemonEntity")
    suspend fun clearAll()

}