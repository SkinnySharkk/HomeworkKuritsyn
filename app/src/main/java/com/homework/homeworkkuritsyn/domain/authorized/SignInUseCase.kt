package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import javax.inject.Inject

interface SignInUseCase {
    suspend fun execute(login: String, password: String): AuthResult
}

class SignInUseCaseImpl @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
) : SignInUseCase {
    override suspend fun execute(login: String, password: String): AuthResult {
        val authEntity = AuthEntity(login, password)
        val result = authorizedRepository.signIn(authEntity)
        if (result is AuthResult.Success) {
            authorizedRepository.setAuthorized()
        }
        return result
    }
}