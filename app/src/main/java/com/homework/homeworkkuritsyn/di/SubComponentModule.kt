package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import com.homework.homeworkkuritsyn.di.historyloans.LoansComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthorizedComponent::class,
        LoansComponent::class
    ]
)
interface SubComponentModule