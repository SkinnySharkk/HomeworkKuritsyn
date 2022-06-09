package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface ShiftService {
    @POST("login")
    suspend fun login(@Body auth: Auth): String

    @POST("registration")
    suspend fun registration(@Body auth: Auth): UserModel

    @POST("loans")
    suspend fun createLoans(): Loan

    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Int): List<Loan>

    @GET("loans/all")
    suspend fun getAllLoans(): Loan

    @GET("loans/conditions")
    suspend fun getConditions(): LoanConditions
}