package com.homework.homeworkkuritsyn.di

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.data.applyloan.ApplyLoanRepositoryImpl
import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.data.historyloans.LoansRepositoryImpl
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCaseImpl
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanRepository
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCaseImpl
import com.homework.homeworkkuritsyn.domain.historyloans.LoansRepository
import com.homework.homeworkkuritsyn.presenters.MainViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface AppModule {
    @Binds
    @Singleton
    fun bindLoansRepository(
        LoansRepositoryImpl: LoansRepositoryImpl
    ): LoansRepository

    @Binds
    @Singleton
    fun bindApplyLoansRepository(
        applyLoanRepositoryImpl: ApplyLoanRepositoryImpl
    ): ApplyLoanRepository

    @Binds
    @Singleton
    fun bindAuthorizedRepository(
        authorizedRepositoryImpl: AuthorizedRepositoryImpl
    ): AuthorizedRepository

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