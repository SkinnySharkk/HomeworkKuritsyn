package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SignUpUseCaseTest {
    @Test
    fun `WHEN execute EXPECTED AuthResult_Success`() = runTest {
        val repository: AuthorizedRepository = mock()
        val authEntity = AuthEntity(name = "r", password = "r")
        whenever(repository.signUp(authEntity)).thenReturn(RegisterResult.Success)

        val useCase = SignUpUseCaseImpl(repository)

        val actual = useCase.execute("r", "r")
        val expected = RegisterResult.Success
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN execute EXPECTED AuthResult_Error`() = runTest {
        val repository: AuthorizedRepository = mock()
        val authEntity = AuthEntity(name = "4r", password = "r")
        whenever(repository.signUp(authEntity)).thenReturn(RegisterResult.HttpError("Не верный логин или пароль"))

        val useCase = SignUpUseCaseImpl(repository)

        val actual = useCase.execute("4r", "r")
        val expected = RegisterResult.HttpError("Не верный логин или пароль")
        assertEquals(expected, actual)
    }
}