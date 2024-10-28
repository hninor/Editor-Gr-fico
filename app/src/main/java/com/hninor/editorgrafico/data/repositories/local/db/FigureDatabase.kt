package com.hninor.editorgrafico.data.repositories.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hninor.editorgrafico.data.entities.Figure
import com.hninor.editorgrafico.data.entities.Point

@Database(entities = [FigureDB::class], version = 1)
abstract class FigureDatabase : RoomDatabase() {
    abstract fun figureDao(): FigureDao
}