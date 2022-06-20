package com.homework.homeworkkuritsyn.domain.applyloan

import javax.inject.Inject

interface GetLoanConditionsUseCase {
    suspend fun execute(): LoanConditionsResult
}

class GetLoanConditionsUseCaseImpl @Inject constructor(
    private val applyLoanRepository: ApplyLoanRepository
) : GetLoanConditionsUseCase {
    override suspend fun execute(): LoanConditionsResult {
        return applyLoanRepository.getCondition()
    }
}