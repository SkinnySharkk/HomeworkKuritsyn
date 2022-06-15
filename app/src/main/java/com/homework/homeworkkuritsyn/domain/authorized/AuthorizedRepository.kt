package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.data.network.models.UserDataModel
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import com.homework.homeworkkuritsyn.domain.entity.UserDataEntity

interface AuthorizedRepository {
    suspend fun isAuthorized(): Boolean
    suspend fun setAuthorized()
    fun getToken(): String
    suspend fun setToken(token: String)
    suspend fun signIn(authEntity: AuthEntity): AuthResult
    suspend fun signUp(authEntity: AuthEntity)
    suspend fun setUserData(userDataEntity: UserDataEntity)
    suspend fun getUserData(): UserDataEntity
}