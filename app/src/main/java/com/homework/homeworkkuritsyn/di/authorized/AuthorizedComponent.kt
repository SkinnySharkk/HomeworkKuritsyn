package com.homework.homeworkkuritsyn.di.authorized

import com.homework.homeworkkuritsyn.ui.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthorizedModule::class])
interface AuthorizedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizedComponent
    }

    fun inject(fragment: LoginFragment)
}