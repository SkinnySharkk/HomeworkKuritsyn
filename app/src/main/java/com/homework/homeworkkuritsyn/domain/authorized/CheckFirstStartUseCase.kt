package com.homework.homeworkkuritsyn.domain.authorized

import javax.inject.Inject

class CheckFirstStartUseCase @Inject constructor(private val authorizedRepository: AuthorizedRepository) {
    fun execute(): Boolean {
        return authorizedRepository.getToken().isEmpty()
    }
}