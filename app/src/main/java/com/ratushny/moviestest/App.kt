package com.ratushny.moviestest

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInjector.initKoin(this)
    }
}