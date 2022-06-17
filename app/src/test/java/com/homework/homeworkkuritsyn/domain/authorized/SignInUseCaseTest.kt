package com.homework.homeworkkuritsyn.domain.authorized

import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class SignInUseCaseTest {

    @Test
    fun `WHEN execute EXPECTED AuthResult_Success`() = runTest {
        val repository: AuthorizedRepository = mock()
        val authEntity = AuthEntity(name = "r", password = "r")
        whenever(repository.signIn(authEntity)).thenReturn(AuthResult.Success)

        val useCase = SignInUseCaseImpl(repository)

        val actual = useCase.execute("r", "r")
        val expected = AuthResult.Success
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN execute EXPECTED AuthResult_Error`() = runTest {
        val repository: AuthorizedRepository = mock()
        val authEntity = AuthEntity(name = "4r", password = "r")
        whenever(repository.signIn(authEntity)).thenReturn(AuthResult.HttpError("Не верный логин или пароль"))

        val useCase = SignInUseCaseImpl(repository)

        val actual = useCase.execute("4r", "r")
        val expected = AuthResult.HttpError("Не верный логин или пароль")
        assertEquals(expected, actual)
    }
}