package com.homework.homeworkkuritsyn.data.network.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.math.BigInteger

data class LoanRequest(
    @SerializedName("amount")
    val amount: BigInteger,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("percent")
    val percent: BigDecimal,
    @SerializedName("period")
    val period: BigInteger,
    @SerializedName("phoneNumber")
    val phoneNumber: String
)
