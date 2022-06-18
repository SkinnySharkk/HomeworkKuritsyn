package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import javax.inject.Inject

interface GetLoanConditionsUseCase {
    suspend fun execute(): LoanConditionsEntity
}

class GetLoanConditionsUseCaseImpl @Inject constructor(
    private val applyLoanRepository: ApplyLoanRepository
) : GetLoanConditionsUseCase {
    override suspend fun execute(): LoanConditionsEntity {
        return applyLoanRepository.getCondition()
    }
}