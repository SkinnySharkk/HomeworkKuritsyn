package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoanUseCase
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
class LoanViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var expected: LoanEntity
    private lateinit var loan: LoanEntity

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        loan =
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
            )

        expected =
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
            )
    }

    @Test
    fun `WHEN LoansViewModel getLoan EXPECT LoanEntity with id 1`() = runTest {
        val useCase: GetLoanUseCase = mock()
        whenever(useCase.execute(1)).thenReturn(loan)

        val loanViewModel = LoanViewModel(useCase)
        loanViewModel.getLoan(1)
        val actual = loanViewModel.loan.value

        assertEquals(expected, actual)
    }
}