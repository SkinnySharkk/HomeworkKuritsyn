package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import javax.inject.Inject

interface ApplyLoanUseCase {
    suspend fun execute(loanRequestEntity: LoanRequestEntity): ApplyLoanResult
}

class ApplyLoanUseCaseImpl @Inject constructor(private val applyLoanRepository: ApplyLoanRepository) :
    ApplyLoanUseCase {
    override suspend fun execute(loanRequestEntity: LoanRequestEntity): ApplyLoanResult {
        return applyLoanRepository.applyLoan(loanRequestEntity)
    }
}