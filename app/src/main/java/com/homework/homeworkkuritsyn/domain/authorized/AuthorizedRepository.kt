package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity

interface AuthorizedRepository {
    suspend fun isAuthorized(): Boolean
    suspend fun setAuthorized()
    fun getToken(): String
    suspend fun setToken(token: String)
    suspend fun signIn(authEntity: AuthEntity): AuthResult
    suspend fun signUp(authEntity: AuthEntity)
    suspend fun deleteUserData()
}