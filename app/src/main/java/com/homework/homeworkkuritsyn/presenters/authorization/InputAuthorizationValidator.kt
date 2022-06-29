package com.homework.homeworkkuritsyn.presenters.authorization

import javax.inject.Inject

class InputAuthorizationValidator @Inject constructor() {
    fun isCorrectLogin(login: String): InputAuthorizationValidatorResult {
//    fun isCorrectLogin(login: String): Boolean {
//        return login.matches(Regex("^(?=.*[A-Za-z0-9]\$)[A-Za-z][A-Za-z\\d.-]{0,19}\$"))
        return if (login.isNullOrEmpty()) {
            InputAuthorizationValidatorResult.IsEmpty
        } else if (!login.matches(Regex("\\S+"))) {
            InputAuthorizationValidatorResult.IsNotCorrect("Логин не должен содержать пробелы")
        } else if (login.matches(Regex("[^A-Za-z0-9]"))) {
            InputAuthorizationValidatorResult.IsNotCorrect("Логин должен состоять только из букв и цифр")
        } else {
            InputAuthorizationValidatorResult.IsCorrect
        }
    }

    fun isCorrectPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Za-z0-9]\$)[A-Za-z][A-Za-z\\d.-]{0,19}\$"))
    }
}

sealed interface InputAuthorizationValidatorResult {
    object IsCorrect : InputAuthorizationValidatorResult
    object IsEmpty : InputAuthorizationValidatorResult
    data class IsNotCorrect(val reason: String) : InputAuthorizationValidatorResult
}