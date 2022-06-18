package com.homework.homeworkkuritsyn.domain.entity

import java.math.BigDecimal

data class LoanEntity(
    val amount: BigDecimal,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent	: BigDecimal,
    val period: Int,
    val phoneNumber: String,
    val state: EnumStateEntity
)
