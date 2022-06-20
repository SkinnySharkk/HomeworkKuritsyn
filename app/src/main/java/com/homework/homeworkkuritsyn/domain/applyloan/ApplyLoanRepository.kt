package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity

interface ApplyLoanRepository {
    suspend fun getCondition(): LoanConditionsResult
    suspend fun applyLoan(loanRequestEntity: LoanRequestEntity): ApplyLoanResult
}