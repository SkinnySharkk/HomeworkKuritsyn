package com.homework.homeworkkuritsyn.di.authorized

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.presenters.LoginViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthorizedModule {
    @Binds
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun binViewModel(viewModel: LoginViewModel): ViewModel
}