package com.homework.homeworkkuritsyn.domain.authorized

import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class CheckIsAuthorizedUseCaseImplTest {
    @Test
    fun `WHEN execute WHEN is authorized EXPECTED true`() {
        val repository: AuthorizedRepository = mock()
        whenever(repository.isAuthorized()).thenReturn(true)

        val useCase = CheckIsAuthorizedUseCaseImpl(repository)

        val actual = useCase.execute()
        val expected = true
        assertEquals(expected, actual)
    }
    @Test
    fun `WHEN execute WHEN is not authorized EXPECTED false`() {
        val repository: AuthorizedRepository = mock()
        whenever(repository.isAuthorized()).thenReturn(false)

        val useCase = CheckIsAuthorizedUseCaseImpl(repository)

        val actual = useCase.execute()
        val expected = false
        assertEquals(expected, actual)
    }
}