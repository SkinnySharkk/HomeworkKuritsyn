package com.homework.homeworkkuritsyn.domain.historyloans

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