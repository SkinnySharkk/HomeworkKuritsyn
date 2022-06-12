package com.homework.homeworkkuritsyn.data.converters

import com.homework.homeworkkuritsyn.data.network.models.EnumState
import com.homework.homeworkkuritsyn.data.network.models.Loan
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity

data class LoanConverter(val loans: List<Loan>)

fun LoanConverter.asModel(): List<LoanEntity> {
    return loans.map { loan ->
        val state = when (loan.state) {
            EnumState.APPROVED -> {
                EnumStateEntity.APPROVED
            }
            EnumState.REGISTERED -> {
                EnumStateEntity.REGISTERED
            }
            EnumState.REJECTED -> {
                EnumStateEntity.REJECTED
            }
        }
        LoanEntity(
            amount = loan.amount,
            date = loan.date,
            firstName = loan.firstName,
            id = loan.id,
            lastName = loan.lastName,
            percent = loan.percent,
            period = loan.period,
            phoneNumber = loan.phoneNumber,
            state = state
        )
    }
}