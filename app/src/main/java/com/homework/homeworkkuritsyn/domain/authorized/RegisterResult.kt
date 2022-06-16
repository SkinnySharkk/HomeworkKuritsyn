package com.homework.homeworkkuritsyn.domain.authorized

sealed interface RegisterResult {
    object Success : RegisterResult
    data class HttpError(val reason: String): RegisterResult
    data class OtherError(val reason: String): RegisterResult
}