package com.kiwi.dailyoffer

import android.app.Application
import com.kiwi.dailyoffer.di.remoteDatasourceModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin

class AppShared : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger()
            androidContext(this@AppShared)
            modules(remoteDatasourceModule)
        }
    }
}