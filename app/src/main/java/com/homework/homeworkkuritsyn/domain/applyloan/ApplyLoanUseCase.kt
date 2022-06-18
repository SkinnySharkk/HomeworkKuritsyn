package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import javax.inject.Inject

interface ApplyLoanUseCase {
    suspend fun execute(loanRequestEntity: LoanRequestEntity): LoanEntity
}

class ApplyLoanUseCaseImpl @Inject constructor(private val applyLoanRepository: ApplyLoanRepository) :
    ApplyLoanUseCase {
    override suspend fun execute(loanRequestEntity: LoanRequestEntity): LoanEntity {
        return applyLoanRepository.applyLoan(loanRequestEntity)
    }
}