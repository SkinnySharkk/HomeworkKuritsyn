package com.homework.homeworkkuritsyn.di.loans

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.data.loans.LoansRepositoryImpl
import com.homework.homeworkkuritsyn.domain.loans.LoansRepository
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import com.homework.homeworkkuritsyn.presenters.loans.LoansViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoansModule {
    @Binds
    @[IntoMap ViewModelKey(LoansViewModel::class)]
    fun bindLoansViewModel(viewModel: LoansViewModel): ViewModel
}