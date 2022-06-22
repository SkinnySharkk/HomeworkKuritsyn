package com.homework.homeworkkuritsyn.domain.historyloans


interface LoansRepository {
    suspend fun getAllLoans(): LoansHistoryResult
    suspend fun getLoan(id: Int): LoanHistoryResult
}
