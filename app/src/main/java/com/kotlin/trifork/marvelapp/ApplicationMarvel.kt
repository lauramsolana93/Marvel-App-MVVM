package com.kotlin.trifork.marvelapp

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class ApplicationMarvel : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidContext(this@ApplicationMarvel)
        }
    }
}