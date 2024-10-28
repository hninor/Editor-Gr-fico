package com.hninor.editorgrafico.data.repositories.remote

import com.hninor.editorgrafico.data.entities.Figure

class RemoteDataSource(private val figureService: FigureService) {

    suspend fun getFigures(): Result<List<Figure>> {
        return try {
            val figures = figureService.getFigures()
            Result.success(figures)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}