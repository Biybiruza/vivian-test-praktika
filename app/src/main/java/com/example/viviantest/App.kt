package com.example.viviantest

import android.app.Application
import com.example.viviantest.di.adapterModule
import com.example.viviantest.di.localModule
import com.example.viviantest.di.remoteModule
import com.example.viviantest.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        val module = listOf(remoteModule, localModule, viewModelModule, adapterModule)
//        val koin: KoinApplication = create(this)
//            .modules(modules)
//        startKoin(koin)
        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            koin.loadModules(module)
        }
    }
}