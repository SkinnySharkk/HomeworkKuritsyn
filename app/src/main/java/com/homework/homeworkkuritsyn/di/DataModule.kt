package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.data.applyloan.ApplyLoanRepositoryImpl
import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.data.historyloans.LoansRepositoryImpl
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanRepository
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.historyloans.LoansRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataModule {
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
}