package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

sealed interface LoansHistoryResult {
    data class Success(val loans: List<LoanEntity>): LoansHistoryResult
    data class Error(val response: String): LoansHistoryResult
}