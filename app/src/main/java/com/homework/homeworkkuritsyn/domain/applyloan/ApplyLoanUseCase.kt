package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import java.math.BigInteger
import javax.inject.Inject

class ApplyLoanUseCase @Inject constructor(private val applyLoanRepository: ApplyLoanRepository) {
    suspend fun execute(loanRequestEntity: LoanRequestEntity): LoanEntity {
        return applyLoanRepository.applyLoan(loanRequestEntity)
    }
}