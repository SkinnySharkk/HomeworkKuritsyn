package com.homework.homeworkkuritsyn.domain.loans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

interface LoansRepository {
    suspend fun getAllLoans(): List<LoanEntity>
    suspend fun getLoan(id: Int): LoanEntity
}
