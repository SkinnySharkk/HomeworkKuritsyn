package com.homework.homeworkkuritsyn.data.converters

import com.homework.homeworkkuritsyn.data.network.models.LoanConditions
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity

data class LoanConditionsConverter(val loanConditions: LoanConditions)


fun LoanConditionsConverter.asEntity(): LoanConditionsEntity {
    return LoanConditionsEntity(
        maxAmount = loanConditions.maxAmount,
        percent = loanConditions.percent,
        period = loanConditions.period
    )
}