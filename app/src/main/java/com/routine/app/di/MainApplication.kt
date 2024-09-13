package com.routine.app.di

import android.app.Application
import com.routine.presentation.di.presentationModule
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            modules(presentationModule)
        }
    }

    companion object {
        var instance: MainApplication? = null
            private set
    }
}
