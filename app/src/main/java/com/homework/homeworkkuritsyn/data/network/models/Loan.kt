package com.homework.homeworkkuritsyn.data.network.models

import java.math.BigDecimal
import java.math.BigInteger

data class Loan(
    val amount: BigInteger,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent	: BigDecimal,
    val period: Int,
    val phoneNumber: String,
    val state: EnumState
)
