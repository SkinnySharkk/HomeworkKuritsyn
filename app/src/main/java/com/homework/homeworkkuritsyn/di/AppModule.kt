package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.data.loans.LoansRepositoryImpl
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.loans.LoansRepository
import dagger.Binds
import dagger.Module
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
}