package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

interface GetLoanUseCase {
    suspend fun execute(id: Int): LoanHistoryResult
}

class GetLoanUseCaseImpl @Inject constructor(private val loansRepository: LoansRepository) :
    GetLoanUseCase {
    override suspend fun execute(id: Int): LoanHistoryResult {
        return loansRepository.getLoan(id)
    }
}