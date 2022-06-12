package com.homework.homeworkkuritsyn.di.loans

import com.homework.homeworkkuritsyn.ui.loans.LoanFragment
import com.homework.homeworkkuritsyn.ui.loans.LoansFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoansModule::class])
interface LoansComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): LoansComponent
    }
    fun inject(fragment: LoansFragment)
    fun inject(fragment: LoanFragment)
}