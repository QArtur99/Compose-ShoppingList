package com.artf.shoppinglistcompose

import android.app.Application
import com.artf.shoppinglistcompose.di.dataModule
import com.artf.shoppinglistcompose.di.uiDataModule
import com.artf.shoppinglistcompose.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(dataModule, uiDataModule, vmModule))
        }
    }
}