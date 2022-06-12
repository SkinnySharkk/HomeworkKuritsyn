package com.homework.homeworkkuritsyn.domain.loans

import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

class GetLoanUseCase @Inject constructor(private val loansRepository: LoansRepository) {
    suspend fun execute(id: Int) : LoanEntity {
        return loansRepository.getLoan(id)
    }
}