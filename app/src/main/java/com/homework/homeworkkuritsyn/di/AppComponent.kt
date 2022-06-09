package com.homework.homeworkkuritsyn.di

import android.content.Context
import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        ViewModelBuilderModule::class,
        SubComponentModule::class,
    NetworkModule::class
    ]
)
interface AppComponent {

    fun authorizedComponent(): AuthorizedComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}