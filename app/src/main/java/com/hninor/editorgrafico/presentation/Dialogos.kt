package com.hninor.editorgrafico.presentation

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolygonInputDialog(
    onNumberOfSidesChanged: (Int) -> Unit
) {
    var numberOfSides by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { /* Handle dismissal */ },
        title = { Text(text = "Número de Lados") },
        text = {
            TextField(
                value = numberOfSides,
                onValueChange = { numberOfSides = it },
                label = { Text(text = "Ingrese el número de lados") }
            )
        },
        confirmButton = {
            Button(onClick = {
                val sides = numberOfSides.toIntOrNull()
                if (sides != null && sides > 2) {
                    onNumberOfSidesChanged(sides)
                } else {
                    // Manejar caso inválido (número menor o igual a 2, o no numérico)
                }
            }) {
                Text(text = "Confirmar")
            }
        }
    )
}