package com.example.chucknorris

import android.app.Application
import com.example.chucknorris.di.modules.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class CNApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CNApplication)
            modules(appModules.allModules())
        }
    }
}