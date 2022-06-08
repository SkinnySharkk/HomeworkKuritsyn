package com.homework.homeworkkuritsyn.domain.entity

import java.math.BigDecimal
import java.math.BigInteger

data class LoanRequestEntity(
val amount: BigInteger,
val firstName: String,
val lastName: String,
val percent: BigDecimal,
val period: BigInteger,
val phoneNumber: String
)
