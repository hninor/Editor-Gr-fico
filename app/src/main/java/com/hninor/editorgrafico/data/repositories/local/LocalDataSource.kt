package com.hninor.editorgrafico.data.repositories.local

import com.google.gson.Gson
import com.hninor.editorgrafico.domain.entities.Figure
import com.hninor.editorgrafico.domain.entities.Point
import com.hninor.editorgrafico.data.repositories.local.db.FigureDB
import com.hninor.editorgrafico.data.repositories.local.db.FigureDao

class LocalDataSource(private val dao: FigureDao) {

    // Puedes agregar más funciones aquí para realizar operaciones con las figuras,
    // como guardarlas localmente, filtrarlas, etc.
    // Por ejemplo:
    suspend fun saveFiguresLocally(figures: List<Figure>) {
        dao.deleteAll()
        // Implementación para guardar las figuras localmente
        figures.forEach { figure ->
            dao.insertFigure(FigureDB(0, figure.name, Gson().toJson(figure.points)))
        }
    }

    suspend fun getFigures(): Result<List<Figure>> {
        val response = mutableListOf<Figure>()
        val figuresDB = dao.getAllFigures()
        figuresDB.forEach {
            response.add(
                Figure(
                    it.name,
                    Gson().fromJson(it.pointsJson, Array<Point>::class.java).toList()
                )
            )
        }

        return Result.success(response)

    }

    suspend fun hayDatosLocales(): Boolean {
        return dao.getAllFigures().isNotEmpty()
    }
}