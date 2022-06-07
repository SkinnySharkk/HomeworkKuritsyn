package com.homework.homeworkkuritsyn.di

import com.homework.homeworkkuritsyn.di.authorized.AuthorizedComponent
import dagger.Module

@Module(subcomponents = [AuthorizedComponent::class])
interface SubComponentModule