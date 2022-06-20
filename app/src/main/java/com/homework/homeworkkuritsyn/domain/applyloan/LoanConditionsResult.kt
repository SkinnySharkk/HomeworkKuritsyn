package com.homework.homeworkkuritsyn.domain.applyloan

import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity

sealed interface LoanConditionsResult {
    data class Success(val conditions: LoanConditionsEntity) : LoanConditionsResult
    data class Error(val response: String) : LoanConditionsResult
}