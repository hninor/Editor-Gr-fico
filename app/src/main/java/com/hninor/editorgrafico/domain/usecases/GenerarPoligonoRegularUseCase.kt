package com.hninor.editorgrafico.domain.usecases


import android.util.Log
import com.hninor.editorgrafico.domain.entities.Figure
import com.hninor.editorgrafico.domain.entities.Point
import kotlin.math.cos
import kotlin.math.sin

class GenerarPoligonoRegularUseCase {

    fun generarDummy(): Figure {
        return Figure("Poligono regular")
    }

    fun generarPoligono(numeroLados: Int): Figure {
        val response = Figure("Poligono regular $numeroLados", generarPuntos(numeroLados))
        Log.d("Poligono Regular", "${response.points}")
        return response
    }

    private fun generarPuntos(numeroLados: Int): List<Point> {
        val response = mutableListOf<Point>()

        val angle = 2 * Math.PI / numeroLados
        for (i in 0 until numeroLados) {
            val x = 0.5  + 0.25 * cos(i * angle)
            val y = 0.764 + 0.25 * sin(i * angle)

            response.add(Point(x.toFloat(), y.toFloat()))
        }
        return response
    }
}