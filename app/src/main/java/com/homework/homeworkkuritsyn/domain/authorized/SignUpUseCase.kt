package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun execute(
        login: String, password: String
    ): RegisterResult
}

class SignUpUseCaseImpl @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
) : SignUpUseCase {
    override suspend fun execute(
        login: String, password: String
    ): RegisterResult {
        val authEntity = AuthEntity(login, password)
        return authorizedRepository.signUp(authEntity)
    }
}