package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.data.authorized.AuthorizedRepositoryImpl
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import dagger.Binds
import dagger.Module

@Module
interface AppModule {
    @Binds
    fun bindAuthorizedRepository(
        authorizedRepositoryImpl: AuthorizedRepositoryImpl
    ): AuthorizedRepository
}