package org.hariswei.cmpshowcaseproject

import android.app.Application

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
//        initKoin {
//            androidContext(this@MyApplication)
//        }
    }
}