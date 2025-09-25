package com.nabila.userpjs

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import com.nabila.userpjs.data.remote.api.appModule

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}