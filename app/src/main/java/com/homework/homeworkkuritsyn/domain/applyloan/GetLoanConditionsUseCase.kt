package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import javax.inject.Inject

class GetLoanConditionsUseCase @Inject constructor(private val applyLoanRepository: ApplyLoanRepository) {
    suspend fun execute(): LoanConditionsEntity {
        return applyLoanRepository.getCondition()
    }
}