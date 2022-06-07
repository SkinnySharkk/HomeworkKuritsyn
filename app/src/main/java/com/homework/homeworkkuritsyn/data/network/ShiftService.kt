package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.Loan
import com.homework.homeworkkuritsyn.data.network.models.LoanConditions
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ShiftService {
    @POST("login/")
    suspend fun login(name: String, password: String)

    @POST("registration/")
    suspend fun registration(name: String, password: String)

    @POST("loans/")
    suspend fun createLoans()

    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Int): List<Loan>

    @GET("loans/all")
    suspend fun getAllLoans(): Loan

    @GET("loans/conditions")
    suspend fun getConditions(): LoanConditions
}