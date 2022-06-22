package com.homework.homeworkkuritsyn.di.main

import com.homework.homeworkkuritsyn.ui.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }
    fun inject(mainActivity: MainActivity)
}