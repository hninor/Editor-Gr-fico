package com.hninor.editorgrafico.data.repositories

import com.hninor.editorgrafico.domain.entities.Figure
import com.hninor.editorgrafico.data.repositories.local.LocalDataSource
import com.hninor.editorgrafico.data.repositories.remote.RemoteDataSource


class FigureRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getFigures(): Result<List<Figure>> {
        if (localDataSource.hayDatosLocales()) {
            return getFiguresLocal()
        } else {
            return getFiguresRemote()
        }

    }

    suspend fun getFiguresRemote(): Result<List<Figure>> {
        val result = remoteDataSource.getFigures()
        if (result.isSuccess) {
            localDataSource.saveFiguresLocally(
                result.getOrDefault(emptyList())
            )
        }
        return remoteDataSource.getFigures()
    }

    suspend fun getFiguresLocal(): Result<List<Figure>> {
        return localDataSource.getFigures()
    }

    suspend fun saveFiguresLocally(figures: List<Figure>) {
        localDataSource.saveFiguresLocally(figures)
    }


}