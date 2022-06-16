package com.homework.homeworkkuritsyn.di.loans

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import com.homework.homeworkkuritsyn.presenters.applyloan.ApplyLoanViewModel
import com.homework.homeworkkuritsyn.presenters.historyloans.LoanViewModel
import com.homework.homeworkkuritsyn.presenters.historyloans.LoansViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoansModule {
    @Binds
    @[IntoMap ViewModelKey(LoansViewModel::class)]
    fun bindLoansViewModel(viewModel: LoansViewModel): ViewModel
    @Binds
    @[IntoMap ViewModelKey(LoanViewModel::class)]
    fun bindLoanViewModel(viewModel: LoanViewModel): ViewModel
    @Binds
    @[IntoMap ViewModelKey(ApplyLoanViewModel::class)]
    fun bindApplyLoanViewModel(viewModel: ApplyLoanViewModel): ViewModel
}