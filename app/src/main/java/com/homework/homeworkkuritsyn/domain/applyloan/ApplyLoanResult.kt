package com.homework.homeworkkuritsyn.domain.applyloan

sealed interface ApplyLoanResult {
    object Success: ApplyLoanResult
    data class Error(val response: String) : ApplyLoanResult
}