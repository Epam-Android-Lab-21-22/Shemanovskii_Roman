package com.beleavemebe.solevarnya.view

import android.app.Application
import android.content.Context
import com.beleavemebe.solevarnya.di.AppComponent
import com.beleavemebe.solevarnya.di.DaggerAppComponent

class App : Application() {
    val appComponent by lazy {
        DaggerAppComponent.factory().create(assets)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
