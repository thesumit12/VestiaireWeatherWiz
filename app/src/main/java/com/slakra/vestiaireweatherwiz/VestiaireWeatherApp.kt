package com.slakra.vestiaireweatherwiz

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.slakra.di.initKoin
import com.slakra.di.loadDataModules
import com.slakra.vestiaireweatherwiz.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class VestiaireWeatherApp: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        startKoin {
            androidContext(this@VestiaireWeatherApp)
            modules(mutableListOf(viewModelModule))
        }
    }

    override fun onCreate() {
        super.onCreate()
        initKoin()
        loadDataModules()
    }
}