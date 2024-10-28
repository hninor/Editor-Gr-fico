package com.hninor.editorgrafico.data.repositories

import com.hninor.editorgrafico.data.entities.Figure
import com.hninor.editorgrafico.data.repositories.local.LocalDataSource
import com.hninor.editorgrafico.data.repositories.remote.RemoteDataSource


class FigureRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getFigures(): Result<List<Figure>> {
        return remoteDataSource.getFigures()
    }

    suspend fun saveFiguresLocally(figures: List<Figure>) {
        localDataSource.saveFiguresLocally(figures)
    }


}