package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }


    @Test
    fun `WHERE login with r and r EXPECT LoginUiState_Success`() = runTest {
        val signInUseCase: SignInUseCase = mock()
        whenever(signInUseCase.execute("r", "r")).thenReturn(AuthResult.Success)


        val loginViewModel = LoginViewModel(signInUseCase)
        val observer = Observer<LoginUiState> {}
        loginViewModel.loginUiState.observeForever(observer)
        loginViewModel.login("r", "r")

        val actual = loginViewModel.loginUiState.value
        val expected = LoginUiState.Success
        loginViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE login with r and r EXPECT LoginUiState_HttpError`() = runTest {
        val signInUseCase: SignInUseCase = mock()
        whenever(
            signInUseCase.execute(
                "r2",
                "r"
            )
        ).thenReturn(AuthResult.HttpError("Не верный логин или пароль"))


        val loginViewModel = LoginViewModel(signInUseCase)
        val observer = Observer<LoginUiState> {}
        loginViewModel.loginUiState.observeForever(observer)
        loginViewModel.login("r2", "r")

        val actual = loginViewModel.loginUiState.value
        val expected = LoginUiState.Error("Не верный логин или пароль")
        loginViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE validData r and r EXPECT true`() = runTest {
        val repository: AuthorizedRepository = mock()

        val useCase = SignInUseCaseImpl(repository)
        val loginViewModel = LoginViewModel(useCase)
        val actual = loginViewModel.validData("r2", "r")

        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE validData emptyString and r EXPECT false`() = runTest {
        val repository: AuthorizedRepository = mock()

        val useCase = SignInUseCaseImpl(repository)
        val loginViewModel = LoginViewModel(useCase)
        val actual = loginViewModel.validData("", "r")

        val expected = false
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE dropError EXPECT LoginUiState_Idle`() = runTest {
        val signInUseCase: SignInUseCase = mock()
        whenever(
            signInUseCase.execute(
                "r2",
                "r"
            )
        ).thenReturn(AuthResult.HttpError("Не верный логин или пароль"))


        val loginViewModel = LoginViewModel(signInUseCase)
        val observer = Observer<LoginUiState> {}
        loginViewModel.loginUiState.observeForever(observer)
        loginViewModel.login("r2", "r")

        loginViewModel.dropError()
        val actual = loginViewModel.loginUiState.value
        val expected = LoginUiState.Idle
        loginViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }
}