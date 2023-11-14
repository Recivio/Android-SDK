package com.recivio.demoapp

import android.app.Application
import android.graphics.Color
import com.recivio.recivio


@Suppress("unused")
class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        recivio.configure(application = this, recivioAppId = recivio_APP_ID, widgetColorInt = Color.RED)
    }

    companion object {
        const val recivio_APP_ID = ""//TODO
    }
}