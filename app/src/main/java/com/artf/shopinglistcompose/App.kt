package com.artf.shopinglistcompose

import android.app.Application
import com.artf.shopinglistcompose.di.dataModule
import com.artf.shopinglistcompose.di.vmModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    private val TAG = App::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(dataModule, vmModule))
        }
    }
}