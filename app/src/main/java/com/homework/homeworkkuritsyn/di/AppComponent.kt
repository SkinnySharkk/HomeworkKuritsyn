package com.homework.homeworkkuritsyn.di

import android.content.Context
import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import com.homework.homeworkkuritsyn.di.loans.LoansComponent
import com.homework.homeworkkuritsyn.ui.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
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
    fun loansComponent(): LoansComponent.Factory
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}