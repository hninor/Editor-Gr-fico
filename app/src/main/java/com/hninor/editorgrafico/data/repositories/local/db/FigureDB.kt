package com.hninor.editorgrafico.data.repositories.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FigureDB(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val pointsJson: String
)