package com.homework.homeworkkuritsyn.di.historyloans

import com.homework.homeworkkuritsyn.ui.applyloan.ApplyLoanFragment
import com.homework.homeworkkuritsyn.ui.historyloans.LoanFragment
import com.homework.homeworkkuritsyn.ui.historyloans.LoansFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoansModule::class])
interface LoansComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoansComponent
    }
    fun inject(fragment: LoansFragment)
    fun inject(fragment: LoanFragment)
    fun inject(fragment: ApplyLoanFragment)
}