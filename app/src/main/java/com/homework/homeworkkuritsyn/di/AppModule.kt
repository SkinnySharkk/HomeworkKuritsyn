package com.homework.homeworkkuritsyn.di

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.data.applyloan.ApplyLoanRepositoryImpl
import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.data.loans.LoansRepositoryImpl
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanRepository
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.loans.LoansRepository
import com.homework.homeworkkuritsyn.presenters.MainViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface AppModule {
    @Binds
    fun bindAuthorizedRepository(
        authorizedRepositoryImpl: AuthorizedRepositoryImpl
    ): AuthorizedRepository
    @Binds
    fun bindLoansRepository(
        LoansRepositoryImpl: LoansRepositoryImpl
    ): LoansRepository
    @Binds
    fun bindApplyLoansRepository(
        applyLoanRepositoryImpl: ApplyLoanRepositoryImpl
    ): ApplyLoanRepository
    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}