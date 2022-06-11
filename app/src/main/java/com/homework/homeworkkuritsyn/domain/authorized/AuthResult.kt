package com.homework.homeworkkuritsyn.domain.authorized

sealed interface AuthResult {
    object Success : AuthResult
    data class HttpError(val reason: String): AuthResult
    data class OtherError(val reason: String): AuthResult
}