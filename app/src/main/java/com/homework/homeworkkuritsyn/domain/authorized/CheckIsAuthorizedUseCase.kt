package com.homework.homeworkkuritsyn.domain.authorized

import javax.inject.Inject

interface CheckIsAuthorizedUseCase {
    fun execute(): Boolean
}
class CheckIsAuthorizedUseCaseImpl @Inject constructor(
    private val authorizedRepository: AuthorizedRepository
    ) : CheckIsAuthorizedUseCase {
    override fun execute(): Boolean {
        return authorizedRepository.isAuthorized()
    }
}