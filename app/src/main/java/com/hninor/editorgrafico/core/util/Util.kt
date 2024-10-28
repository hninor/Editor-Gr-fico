package com.hninor.editorgrafico.core.util

import com.hninor.editorgrafico.domain.entities.Point
import kotlin.math.atan2



fun convexHull(points: List<Point>): List<Point> {
    if (points.size < 3) {
        return points
    }

    // Find the point with the lowest y-coordinate (or lexicographically smallest if ties)
    val p0 = points.minByOrNull { it.y }!!

    // Sort the points by polar angle with respect to p0
    val sortedPoints = points.sortedBy {
        atan2(
            (it.y - p0.y).toDouble(),
            (it.x - p0.x).toDouble()
        )
    }

    return sortedPoints
}