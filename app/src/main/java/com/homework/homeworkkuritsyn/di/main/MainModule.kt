package com.homework.homeworkkuritsyn.di.main

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCaseImpl
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCaseImpl
import com.homework.homeworkkuritsyn.presenters.MainViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {
    @Binds
    fun bindCheckFirstStartUseCase(
        checkFirstStartUseCaseImpl: CheckFirstStartUseCaseImpl
    ): CheckFirstStartUseCase

    @Binds
    fun bindDeleteUserDataUseCase(
        deleteUserDataUseCaseImpl: DeleteUserDataUseCaseImpl
    ): DeleteUserDataUseCase

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}