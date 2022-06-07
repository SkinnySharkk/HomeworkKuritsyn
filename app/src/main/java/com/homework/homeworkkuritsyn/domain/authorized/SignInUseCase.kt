package com.homework.homeworkkuritsyn.domain.authorized

import javax.inject.Inject

class SignInUseCase @Inject constructor(private val authorizedRepository: AuthorizedRepository) {
    fun execute(name: String, password: String) {
        authorizedRepository.signIn(name, password)
    }
}