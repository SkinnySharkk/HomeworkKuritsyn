package com.homework.homeworkkuritsyn.di.profile

import com.homework.homeworkkuritsyn.ui.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): ProfileComponent
    }

    fun inject(fragment: ProfileFragment)
}