package com.hninor.editorgrafico.data.repositories.local

import com.google.gson.Gson
import com.hninor.editorgrafico.data.entities.Figure
import com.hninor.editorgrafico.data.repositories.local.db.FigureDB
import com.hninor.editorgrafico.data.repositories.local.db.FigureDao

class LocalDataSource(private val dao: FigureDao) {

    // Puedes agregar más funciones aquí para realizar operaciones con las figuras,
    // como guardarlas localmente, filtrarlas, etc.
    // Por ejemplo:
    suspend fun saveFiguresLocally(figures: List<Figure>) {
        // Implementación para guardar las figuras localmente
        figures.forEach { figure ->
            dao.insertFigure(FigureDB(0, figure.name, Gson().toJson(figure.points)))
        }
    }
}