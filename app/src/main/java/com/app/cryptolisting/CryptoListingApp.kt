package com.app.cryptolisting

import android.app.Application
import com.app.cryptolisting.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CryptoListingApp:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CryptoListingApp)
            androidLogger()

            modules(appModule)
        }
    }

}