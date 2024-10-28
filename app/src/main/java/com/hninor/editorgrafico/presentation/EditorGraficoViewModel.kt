package com.hninor.editorgrafico.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hninor.editorgrafico.data.entities.Figure
import com.hninor.editorgrafico.data.repositories.FigureRepository
import kotlinx.coroutines.launch


class EditorGraficoViewModel(
    private val figureRepository: FigureRepository
) : ViewModel() {

    val figures = mutableStateListOf<Figure>()

    init {
        fetchFigures()
    }

    fun fetchFigures() {
        viewModelScope.launch {
            val result = figureRepository.getFigures()

            if (result.isSuccess) {
                val figuresDownloaded = result.getOrDefault(emptyList())
                figures.clear()
                figures.addAll(figuresDownloaded)

            } else {
                // Handle error
                Log.e("ViewModel", "Error fetching figures: ${result.exceptionOrNull()}")
            }
        }
    }
}