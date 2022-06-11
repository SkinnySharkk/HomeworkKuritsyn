package com.homework.homeworkkuritsyn.domain.loans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

class GetLoansUseCase @Inject constructor(private val loansRepository: LoansRepository) {
    suspend fun execute(): List<LoanEntity> {
       return loansRepository.getAllLoans()
    }
}