package com.homework.homeworkkuritsyn.domain.loans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

interface GetLoanUseCase {
    suspend fun execute(id: Int): LoanEntity
}

class GetLoanUseCaseImpl @Inject constructor(private val loansRepository: LoansRepository) :
    GetLoanUseCase {
    override suspend fun execute(id: Int): LoanEntity {
        return loansRepository.getLoan(id)
    }
}