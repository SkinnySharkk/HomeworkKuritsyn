package com.homework.homeworkkuritsyn.domain.historyloans

import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

sealed interface LoanHistoryResult {
    data class Success(val loan: LoanEntity): LoanHistoryResult
    data class Error(val response: String): LoanHistoryResult
}