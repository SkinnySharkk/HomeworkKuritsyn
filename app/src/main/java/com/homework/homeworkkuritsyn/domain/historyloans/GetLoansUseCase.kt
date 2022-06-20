package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

interface GetLoansUseCase {
    suspend fun execute(): LoansHistoryResult
}

class GetLoansUseCaseImpl @Inject constructor(private val loansRepository: LoansRepository) :
    GetLoansUseCase {
    override suspend fun execute(): LoansHistoryResult {
        return loansRepository.getAllLoans()
    }
}