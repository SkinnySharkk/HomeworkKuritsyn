package com.homework.homeworkkuritsyn.di.authorized

import com.homework.homeworkkuritsyn.ui.authorization.LoginFragment
import com.homework.homeworkkuritsyn.ui.authorization.RegisterFragment
import dagger.Subcomponent

@Subcomponent(modules = [AuthorizedModule::class])
interface AuthorizedComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): AuthorizedComponent
    }

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)
}