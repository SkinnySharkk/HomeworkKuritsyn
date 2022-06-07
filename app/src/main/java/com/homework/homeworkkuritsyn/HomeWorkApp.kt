package com.homework.homeworkkuritsyn

import android.app.Application
import android.content.Context
import com.homework.homeworkkuritsyn.di.AppComponent
import com.homework.homeworkkuritsyn.di.DaggerAppComponent
import timber.log.Timber.*
import timber.log.Timber.Forest.plant


class HomeWorkApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            plant(DebugTree())
        }
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is HomeWorkApp -> appComponent
        else -> this.applicationContext.appComponent
    }