package com.homework.homeworkkuritsyn.domain.authorized

import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authorizedRepository: AuthorizedRepository) {
    fun execute(name: String, password: String) {
        authorizedRepository.signUp(name, password)
    }
}