package com.hninor.editorgrafico.data.repositories.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FigureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFigure(figure: FigureDB)

    @Query("SELECT * FROM FigureDB")
    suspend fun getAllFigures(): List<FigureDB>

    @Query("DELETE FROM FigureDB")
    suspend fun deleteAll()

    // ... other queries as needed
}