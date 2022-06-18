package com.homework.homeworkkuritsyn.domain

import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import javax.inject.Inject

interface DeleteUserDataUseCase {
    fun execute()
}
class DeleteUserDataUseCaseImpl @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
) : DeleteUserDataUseCase{
    override fun execute() {
        authorizedRepository.deleteUserData()
    }
}