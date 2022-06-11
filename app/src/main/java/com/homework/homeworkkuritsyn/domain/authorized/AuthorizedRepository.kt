package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity

interface AuthorizedRepository {
    fun isAuthorized() : Boolean
    fun setAuthorized()
    fun getToken() : String
    fun setToken(token: String)
    suspend fun signIn(authEntity: AuthEntity)
    suspend fun signUp(authEntity: AuthEntity)
}