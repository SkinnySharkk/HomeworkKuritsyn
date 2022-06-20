package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

interface LoansRepository {
    suspend fun getAllLoans(): LoansHistoryResult
    suspend fun getLoan(id: Int): LoanHistoryResult
}
