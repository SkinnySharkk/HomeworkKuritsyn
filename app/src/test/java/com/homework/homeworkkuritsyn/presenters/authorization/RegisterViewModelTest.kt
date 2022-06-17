package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.homework.homeworkkuritsyn.domain.authorized.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class RegisterViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }


    @Test
    fun `WHERE login with r and r EXPECT LoginUiState_Success`() = runTest {
        val signUpUseCase: SignUpUseCase = mock()
        whenever(signUpUseCase.execute("r", "r")).thenReturn(RegisterResult.Success)


        val registerViewModel = RegisterViewModel(signUpUseCase)
        val observer = Observer<LoginUiState> {}
        registerViewModel.loginUiState.observeForever(observer)
        registerViewModel.register("r", "r")

        val actual = registerViewModel.loginUiState.value
        val expected = LoginUiState.Success
        registerViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE login with r and r EXPECT LoginUiState_HttpError`() = runTest {
        val signUpUseCase: SignUpUseCase = mock()
        whenever(
            signUpUseCase.execute(
                "r2",
                "r"
            )
        ).thenReturn(RegisterResult.HttpError("Такой пользователь уже существует"))


        val registerViewModel = RegisterViewModel(signUpUseCase)
        val observer = Observer<LoginUiState> {}
        registerViewModel.loginUiState.observeForever(observer)
        registerViewModel.register("r2", "r")

        val actual = registerViewModel.loginUiState.value
        val expected = LoginUiState.Error("Такой пользователь уже существует")
        registerViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE validData r and r EXPECT true`() = runTest {
        val repository: AuthorizedRepository = mock()

        val useCase = SignUpUseCaseImpl(repository)
        val registerViewModel = RegisterViewModel(useCase)
        val actual = registerViewModel.validData("r2", "r")

        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE validData emptyString and r EXPECT false`() = runTest {
        val repository: AuthorizedRepository = mock()

        val useCase = SignUpUseCaseImpl(repository)
        val registerViewModel = RegisterViewModel(useCase)
        val actual = registerViewModel.validData("", "r")

        val expected = false
        assertEquals(expected, actual)
    }

    @Test
    fun `WHERE dropError EXPECT LoginUiState_Idle`() = runTest {
        val signUpUseCase: SignUpUseCase = mock()
        whenever(
            signUpUseCase.execute(
                "r2",
                "r"
            )
        ).thenReturn(RegisterResult.HttpError("Такой пользователь уже существует"))


        val registerViewModel = RegisterViewModel(signUpUseCase)
        val observer = Observer<LoginUiState> {}
        registerViewModel.loginUiState.observeForever(observer)
        registerViewModel.register("r2", "r")

        registerViewModel.dropError()
        val actual = registerViewModel.loginUiState.value
        val expected = LoginUiState.Idle
        registerViewModel.loginUiState.removeObserver(observer)
        assertEquals(expected, actual)
    }
}