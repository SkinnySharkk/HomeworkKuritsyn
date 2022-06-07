package com.homework.homeworkkuritsyn.domain.authorized

interface AuthorizedRepository {
    fun isAuthorized() : Boolean
    fun setAuthorized()
    fun getToken() : String
    fun setToken(token: String)
    fun signIn(name: String, password: String)
    fun signUp(name: String, password: String)
}