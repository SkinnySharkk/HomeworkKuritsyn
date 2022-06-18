package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.Auth
import com.homework.homeworkkuritsyn.data.network.models.Loan
import com.homework.homeworkkuritsyn.data.network.models.LoanConditions
import com.homework.homeworkkuritsyn.data.network.models.LoanRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShiftService {
    @POST("login")
    suspend fun login(@Body auth: Auth): String

    @POST("registration")
    suspend fun registration(@Body auth: Auth)

    @POST("loans")
    suspend fun createLoans(@Body loanRequest: LoanRequest): Loan

    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Int): Loan

    @GET("loans/all")
    suspend fun getAllLoans(): List<Loan>

    @GET("loans/conditions")
    suspend fun getConditions(): LoanConditions
}