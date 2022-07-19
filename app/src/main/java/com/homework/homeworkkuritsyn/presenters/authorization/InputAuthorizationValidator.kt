package com.homework.homeworkkuritsyn.presenters.authorization

import javax.inject.Inject

class InputAuthorizationValidator @Inject constructor() {
    fun isCorrectLogin(login: String): Boolean {
        return login.matches(Regex("^(?=.*[A-Za-z0-9]\$)[A-Za-z][A-Za-z\\d.-]{1,16}\$"))
    }

    fun isCorrectPassword(password: String): Boolean {
        return password.matches(Regex("^(?=.*[A-Za-z0-9]\$)[A-Za-z][A-Za-z\\d.-]{4,32}\$"))
    }
}