package com.hninor.editorgrafico.data.entities

data class Figure(
    val name: String = "",
    val points: List<Point> = emptyList()
)


data class Point(
    val x: Float,
    val y: Float,
)