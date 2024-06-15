package com.ookie.sl4gcompose

import android.app.Application
import com.ookie.sl4gcompose.data.AppContainer
import com.ookie.sl4gcompose.data.SL4GAppContainer

class SL4GApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = SL4GAppContainer()
    }
}