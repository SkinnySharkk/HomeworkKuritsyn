package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

interface GetLoansUseCase {
    suspend fun execute(): List<LoanEntity>
}

class GetLoansUseCaseImpl @Inject constructor(private val loansRepository: LoansRepository) :
    GetLoansUseCase {
    override suspend fun execute(): List<LoanEntity> {
        return loansRepository.getAllLoans()
    }
}