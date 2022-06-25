package com.homework.homeworkkuritsyn.di

import android.content.Context
import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import com.homework.homeworkkuritsyn.di.historyloans.LoansComponent
import com.homework.homeworkkuritsyn.di.main.MainComponent
import com.homework.homeworkkuritsyn.di.profile.ProfileComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        SubComponentModule::class,
        NetworkModule::class,
        DispatchersModule::class,
        DataModule::class
    ]
)
interface AppComponent {

    fun authorizedComponent(): AuthorizedComponent.Factory
    fun loansComponent(): LoansComponent.Factory
    fun mainComponent(): MainComponent.Factory
    fun profileComponent(): ProfileComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}