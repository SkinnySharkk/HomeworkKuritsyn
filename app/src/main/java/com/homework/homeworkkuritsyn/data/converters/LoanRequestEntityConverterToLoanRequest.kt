package com.homework.homeworkkuritsyn.data.converters

import com.homework.homeworkkuritsyn.data.network.models.LoanRequest
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity

data class LoanRequestEntityConverterToLoanRequest(val loanEntity: LoanRequestEntity)
fun LoanRequestEntityConverterToLoanRequest.asModel(): LoanRequest {
    return LoanRequest(
        amount = loanEntity.amount,
        firstName = loanEntity.firstName,
        lastName = loanEntity.lastName,
        percent = loanEntity.percent,
        period = loanEntity.period,
        phoneNumber = loanEntity.phoneNumber
    )
}