package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCase
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
import java.math.BigDecimal

@ExperimentalCoroutinesApi
class LoansViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val loans = mutableListOf<LoanEntity>()
    private val expected = mutableListOf<LoanEntity>()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loans.addAll(
            listOf(
                LoanEntity(
                    amount = BigDecimal.TEN,
                    date = "01.05.22",
                    firstName = "Bob",
                    lastName = "Faust",
                    id = 1,
                    percent = BigDecimal.valueOf(12.3),
                    period = 46,
                    phoneNumber = "89136785438",
                    state = EnumStateEntity.REGISTERED
                ),
                LoanEntity(
                    amount = BigDecimal.ONE,
                    date = "05.02.21",
                    firstName = "Mike",
                    lastName = "Miller",
                    id = 2,
                    percent = BigDecimal.valueOf(10.5),
                    period = 52,
                    phoneNumber = "89518217435",
                    state = EnumStateEntity.REJECTED
                )
            )
        )
        expected.addAll(
            listOf(
                LoanEntity(
                    amount = BigDecimal.TEN,
                    date = "01.05.22",
                    firstName = "Bob",
                    lastName = "Faust",
                    id = 1,
                    percent = BigDecimal.valueOf(12.3),
                    period = 46,
                    phoneNumber = "89136785438",
                    state = EnumStateEntity.REGISTERED
                ),
                LoanEntity(
                    amount = BigDecimal.ONE,
                    date = "05.02.21",
                    firstName = "Mike",
                    lastName = "Miller",
                    id = 2,
                    percent = BigDecimal.valueOf(10.5),
                    period = 52,
                    phoneNumber = "89518217435",
                    state = EnumStateEntity.REJECTED
                )
            )
        )
    }

    @Test
    fun `WHEN created LoansViewModel not null`() = runTest {
        val useCase: GetLoansUseCase = mock()
        whenever(useCase.execute()).thenReturn(loans)

        val loansViewModel = LoansViewModel(useCase)
        val actual = loansViewModel.loans.value

        assertEquals(expected, actual)
    }


    @Test
    fun `WHEN LoansViewModel updateLoans EXPECT not null`() = runTest {
        val useCase: GetLoansUseCase = mock()
        whenever(useCase.execute()).thenReturn(loans)

        val loansViewModel = LoansViewModel(useCase)
        loansViewModel.updateLoans()
        val actual = loansViewModel.loans.value

        assertEquals(expected, actual)
    }


}