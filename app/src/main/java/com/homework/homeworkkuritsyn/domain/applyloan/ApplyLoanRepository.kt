package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity

interface ApplyLoanRepository {
    suspend fun getCondition(): LoanConditionsEntity
    suspend fun applyLoan(loanRequestEntity: LoanRequestEntity): LoanEntity
}