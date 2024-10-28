package com.hninor.editorgrafico.domain.usecases

import android.R.attr.radius
import com.hninor.editorgrafico.domain.entities.Figure
import com.hninor.editorgrafico.domain.entities.Point
import java.lang.Math.cos
import java.lang.Math.sin


class GenerarPoligonoRegularUseCase {

    fun ejecutar(numeroLados: Int): Figure {
        val response = Figure("Poligono regular", generarPuntos(numeroLados))
        return response
    }

    private fun generarPuntos(numeroLados: Int): List<Point> {
        val response = mutableListOf<Point>()

        val angle = 2 * Math.PI / numeroLados
        for (i in 0 until numeroLados) {
            val x = 0.5 + 0.25 * cos(i * angle)
            val y = 0.5 + 0.25 * sin(i * angle)

            response.add(Point(x.toFloat(), y.toFloat()))
        }
        return response
    }
}