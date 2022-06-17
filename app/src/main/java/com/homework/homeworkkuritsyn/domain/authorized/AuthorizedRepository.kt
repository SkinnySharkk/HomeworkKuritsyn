package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity

interface AuthorizedRepository {
    fun isAuthorized(): Boolean
    fun setAuthorized()
    fun getToken(): String
    fun setToken(token: String)
    fun deleteUserData()
    suspend fun signIn(authEntity: AuthEntity): AuthResult
    suspend fun signUp(authEntity: AuthEntity): RegisterResult
}