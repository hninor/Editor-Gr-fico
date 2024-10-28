package com.hninor.editorgrafico.core.di

import androidx.room.Room
import com.hninor.editorgrafico.data.repositories.FigureRepository
import com.hninor.editorgrafico.data.repositories.local.LocalDataSource
import com.hninor.editorgrafico.data.repositories.local.db.FigureDatabase
import com.hninor.editorgrafico.data.repositories.remote.RemoteDataSource
import com.hninor.editorgrafico.data.repositories.remote.provideFigureService
import com.hninor.editorgrafico.presentation.EditorGraficoViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            androidApplication().applicationContext,
            FigureDatabase::class.java,
            "figure.db"
        ).fallbackToDestructiveMigration().build()
    }

    single {
        val db: FigureDatabase = get()
        db.figureDao()
    }

    viewModel { EditorGraficoViewModel(get()) }
    factory { FigureRepository(get(), get()) }
    single { provideFigureService() }

    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
}
