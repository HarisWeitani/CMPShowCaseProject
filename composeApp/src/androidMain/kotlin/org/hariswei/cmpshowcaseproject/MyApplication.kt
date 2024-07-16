package org.hariswei.cmpshowcaseproject

import android.app.Application
import org.hariswei.cmpshowcaseproject.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApplication)
        }
    }
}