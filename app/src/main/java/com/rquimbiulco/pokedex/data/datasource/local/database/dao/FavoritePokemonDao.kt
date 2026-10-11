package com.rquimbiulco.pokedex.data.datasource.local.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rquimbiulco.pokedex.data.datasource.local.database.entity.FavoritePokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritePokemonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(pokemon: FavoritePokemonEntity)

    @Query("DELETE FROM FavoritePokemonEntity WHERE id = :id")
    suspend fun deleteFavorite(id: Int)

    @Query("SELECT * FROM FavoritePokemonEntity ORDER BY id ASC")
    fun getFavoritesPagingSource(): PagingSource<Int, FavoritePokemonEntity>

    @Query("SELECT EXISTS(SELECT 1 FROM FavoritePokemonEntity WHERE id = :id)")
    fun isFavorite(id: Int): Flow<Boolean>
}