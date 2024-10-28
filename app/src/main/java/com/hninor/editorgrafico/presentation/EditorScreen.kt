package com.hninor.editorgrafico.presentation

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hninor.editorgrafico.data.entities.Figure
import com.hninor.editorgrafico.data.entities.Point
import com.hninor.editorgrafico.ui.theme.EditorGraficoTheme
import kotlin.math.abs
import kotlin.math.atan2

@Composable
fun DesignScreen(figures: List<Figure>) {

    var figureSelected by remember { mutableStateOf(Figure()) }


    val offsetList by remember {
        mutableStateOf(mutableListOf<Offset>())
    }

    var indexPointSelected = 0

    var width = 1f
    var height = 1f
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .weight(10f)
                .pointerInput(Unit) {

                    detectDragGestures(
                        onDragStart = { offset ->

                            offsetList.forEachIndexed { index, element ->
                                if (abs(element.x - offset.x) < 50 && abs(element.y - offset.y) < 50) {
                                    Log.d("Drag and drop", "Point found: $element")
                                    indexPointSelected = index
                                    return@forEachIndexed
                                }
                            }

                        },
                        onDrag = { change, dragAmount ->
                            offsetList[indexPointSelected] = Offset(
                                offsetList[indexPointSelected].x + dragAmount.x,
                                offsetList[indexPointSelected].y + dragAmount.y
                            )

                            val newPoints = offsetList.map {
                                Point(it.x / width, it.y / height)
                            }

                            figureSelected = figureSelected.copy(points = newPoints)
                        },
                        onDragEnd = {

                        })
                }
        ) {


            width = size.width
            height = size.height

            if (figureSelected.points.isNotEmpty()) {


                val orderedPoints = convexHull(figureSelected.points)

                offsetList.clear()

                orderedPoints.forEach { point ->
                    offsetList.add(Offset(point.x * width, point.y * height))
                }


                drawPoints(
                    points = offsetList,
                    pointMode = PointMode.Points,
                    color = Color.Red,
                    strokeWidth = 30f,
                    cap = StrokeCap.Round
                )



                for (i in 0 until offsetList.size - 1) {
                    drawLine(
                        start = offsetList[i],
                        end = offsetList[i + 1],
                        strokeWidth = 10f,
                        color = Color.Red
                    )
                }


                drawLine(
                    start = offsetList[offsetList.size - 1],
                    end = offsetList[0],
                    strokeWidth = 10f,
                    color = Color.Red
                )

            }


        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .weight(1f),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(figures) { figure ->
                FigureItem(figure) {
                    figureSelected = it
                }
            }
        }

    }

}


@Composable
fun FigureItem(figure: Figure, onFigureSelected: (figure: Figure) -> Unit) {
    Card(modifier = Modifier
        .fillMaxHeight()
        .clickable {
            onFigureSelected(figure)
        }) {
        Column(
            modifier = Modifier
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = figure.name)
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DesignPreview() {
    EditorGraficoTheme {
        DesignScreen(figures)
    }
}

val figures = listOf(
    Figure("Figura 1", emptyList()),
    Figure("Figura 2", emptyList()),
    Figure("Figura 3", emptyList()),
    Figure("Figura 4", emptyList()),
    Figure("Figura 5", emptyList()),
    Figure("Figura 6", emptyList())
)


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


