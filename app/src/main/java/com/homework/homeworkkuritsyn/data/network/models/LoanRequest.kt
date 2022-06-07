package com.homework.homeworkkuritsyn.data.network.models

import java.math.BigDecimal
import java.math.BigInteger

data class LoanRequest(
val amount: BigInteger,
val firstName: String,
val lastName: String,
val percent: BigDecimal,
val period: BigInteger,
val phoneNumber: String
)
