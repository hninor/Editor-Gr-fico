package com.hninor.editorgrafico.core

import android.app.Application
import com.hninor.editorgrafico.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class EditorApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@EditorApplication)
            modules(appModule)
        }
    }


}