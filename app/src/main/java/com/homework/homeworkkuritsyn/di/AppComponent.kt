package com.homework.homeworkkuritsyn.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}