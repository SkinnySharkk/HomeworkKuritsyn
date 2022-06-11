package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
) {
    suspend fun execute(authEntity: AuthEntity) : AuthResult {
       return authorizedRepository.signIn(authEntity)
    }
}