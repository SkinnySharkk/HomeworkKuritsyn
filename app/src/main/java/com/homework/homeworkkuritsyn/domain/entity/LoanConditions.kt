package com.homework.homeworkkuritsyn.domain.entity

import java.math.BigDecimal
import java.math.BigInteger

data class LoanConditionsEntity(
    val maxAmount: BigInteger,
    val percent: BigDecimal,
    val period: Int
)
