package com.homework.homeworkkuritsyn.domain.authorized

import javax.inject.Inject

interface CheckFirstStartUseCase {
    fun execute(): Boolean
}
class CheckFirstStartUseCaseImpl @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
    ) : CheckFirstStartUseCase {
    override fun execute(): Boolean {
        return authorizedRepository.isAuthorized()
    }
}