package com.homework.homeworkkuritsyn.domain

import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import javax.inject.Inject

class DeleteUserDataUseCase @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
) {
    suspend fun execute() {
        authorizedRepository.deleteUserData()
    }
}