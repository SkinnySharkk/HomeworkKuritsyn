package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val authorizedRepository: AuthorizedRepository) {
    suspend fun execute(authEntity: AuthEntity) {
        authorizedRepository.signUp(authEntity)
    }
}