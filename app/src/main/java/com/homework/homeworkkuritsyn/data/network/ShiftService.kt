package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.Auth
import com.homework.homeworkkuritsyn.data.network.models.Loan
import com.homework.homeworkkuritsyn.data.network.models.LoanConditions
import com.homework.homeworkkuritsyn.data.network.models.UserModel
import okhttp3.MultipartBody
import retrofit2.http.*

interface ShiftService {
    @Multipart
    @POST("login/")
    suspend fun login(@Part auth: MultipartBody.Part): String

    @Multipart
    @POST("registration/")
    suspend fun registration(@Part auth: MultipartBody.Part): UserModel

    @POST("loans/")
    suspend fun createLoans(): Loan

    @GET("loans/{id}")
    suspend fun getLoan(@Path("id") id: Int): List<Loan>

    @GET("loans/all")
    suspend fun getAllLoans(): Loan

    @GET("loans/conditions")
    suspend fun getConditions(): LoanConditions
}