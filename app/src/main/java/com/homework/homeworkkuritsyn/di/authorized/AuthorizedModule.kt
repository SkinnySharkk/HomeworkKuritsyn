package com.homework.homeworkkuritsyn.di.authorized

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCaseImpl
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCase
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCaseImpl
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import com.homework.homeworkkuritsyn.presenters.authorization.LoginViewModel
import com.homework.homeworkkuritsyn.presenters.authorization.RegisterViewModel
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
    @Binds
    fun bindSignInUseCase(signInUseCaseImpl: SignInUseCaseImpl): SignInUseCase
    @Binds
    fun bindSignUpUseCase(signUpUseCaseImpl: SignUpUseCaseImpl): SignUpUseCase
}