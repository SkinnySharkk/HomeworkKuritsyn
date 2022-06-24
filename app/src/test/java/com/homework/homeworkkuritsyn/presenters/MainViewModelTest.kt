package com.homework.homeworkkuritsyn.presenters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCaseImpl
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.authorized.CheckIsAuthorizedUseCase
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
class MainViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `WHEN checkIsFirstStart is authorized true`() {
        val useCaseCheck: CheckIsAuthorizedUseCase = mock()
        val useCaseDelete: DeleteUserDataUseCase = mock()
        whenever(useCaseCheck.execute()).thenReturn(true)

        val mainViewModel = MainViewModel(useCaseCheck, useCaseDelete)
        val actual = mainViewModel.isAuthorized()
        assertTrue(actual)
    }

    @Test
    fun `WHEN checkIsFirstStart is not authorized false`() {
        val useCaseCheck: CheckIsAuthorizedUseCase = mock()
        val useCaseDelete: DeleteUserDataUseCase = mock()
        whenever(useCaseCheck.execute()).thenReturn(false)

        val mainViewModel = MainViewModel(useCaseCheck, useCaseDelete)
        val actual = mainViewModel.isAuthorized()
        assertFalse(actual)
    }

    @Test
    fun `WHEN deleteUserData EXPECTED token and flagAuthorized is clear`() = runTest {
        val useCaseCheck: CheckIsAuthorizedUseCase = mock()
        val repository: AuthorizedRepository = mock()

        repository.setAuthorized()
        repository.setToken("token")
        val useCaseDelete: DeleteUserDataUseCase = DeleteUserDataUseCaseImpl(repository)
        val mainViewModel = MainViewModel(useCaseCheck, useCaseDelete)
        mainViewModel.deleteUserData()

        val actualAuthorized = repository.isAuthorized()
        val actualToken = repository.getToken()
        assertFalse(actualAuthorized)
        assertNull(actualToken)
    }
}