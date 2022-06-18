package com.homework.homeworkkuritsyn.di.historyloans

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanUseCaseImpl
import com.homework.homeworkkuritsyn.domain.applyloan.GetLoanConditionsUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.GetLoanConditionsUseCaseImpl
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoanUseCase
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoanUseCaseImpl
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCase
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCaseImpl
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

    @Binds
    fun bindGetLoansUseCase(getLoansUseCaseImpl: GetLoansUseCaseImpl): GetLoansUseCase

    @Binds
    fun bindGetLoanUseCase(getLoanUseCaseImpl: GetLoanUseCaseImpl): GetLoanUseCase

    @Binds
    fun bindGetLoanConditionsUseCase(getLoanConditionsUseCaseImpl: GetLoanConditionsUseCaseImpl): GetLoanConditionsUseCase

    @Binds
    fun bindApplyLoanUseCase(applyLoanUseCaseImpl: ApplyLoanUseCaseImpl): ApplyLoanUseCase
}