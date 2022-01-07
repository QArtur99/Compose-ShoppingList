package com.artf.shoppinglistcompose

import android.app.Application
import com.artf.shoppinglistcompose.di.dataModule
import com.artf.shoppinglistcompose.di.uiDataModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(dataModule, uiDataModule))
        }
    }
}