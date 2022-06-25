package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import com.homework.homeworkkuritsyn.di.historyloans.LoansComponent
import com.homework.homeworkkuritsyn.di.main.MainComponent
import com.homework.homeworkkuritsyn.di.profile.ProfileComponent
import dagger.Module

@Module(
    subcomponents = [
        AuthorizedComponent::class,
        LoansComponent::class,
        MainComponent::class,
        ProfileComponent::class
    ]
)
interface SubComponentModule