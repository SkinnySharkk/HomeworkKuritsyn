package com.homework.homeworkkuritsyn.di.authorized

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.presenters.authorization.LoginViewModel
import com.homework.homeworkkuritsyn.presenters.authorization.RegisterViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AuthorizedModule {
    @Binds
    @[IntoMap ViewModelKey(LoginViewModel::class)]
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
    @Binds
    @[IntoMap ViewModelKey(RegisterViewModel::class)]
    fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel
}