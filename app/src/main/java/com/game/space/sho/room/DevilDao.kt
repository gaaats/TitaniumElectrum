package com.game.space.sho.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DevilDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(dataForSave: DevilEntity)

    @Query("SELECT * FROM devil_table")
    suspend fun getAllDevilData(): List<DevilEntity>
}