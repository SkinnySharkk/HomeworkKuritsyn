package com.homework.homeworkkuritsyn.di

import androidx.lifecycle.ViewModelProvider
import com.homework.homeworkkuritsyn.presenters.MultiViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelBuilderModule {
    @Binds
    fun bindViewModelFactory(
        factory: MultiViewModelFactory
    ) : ViewModelProvider.Factory
}