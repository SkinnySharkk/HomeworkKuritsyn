package com.homework.homeworkkuritsyn.domain

import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Test
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class DeleteUserDataUseCaseImplTest {
    @Test
    fun `WHEN execute token and flagAuthorized is clear`() = runTest {
        val repository: AuthorizedRepository = mock()

        val useCase = DeleteUserDataUseCaseImpl(repository)
        repository.setAuthorized()
        repository.setToken("token")
        useCase.execute()
        val actualAuthorized = repository.isAuthorized()
        val actualToken = repository.getToken()

        assertFalse(actualAuthorized)
        assertNull(actualToken)
    }
}