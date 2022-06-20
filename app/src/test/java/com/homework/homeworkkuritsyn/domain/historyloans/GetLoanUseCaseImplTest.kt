package com.homework.homeworkkuritsyn.domain.historyloans

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
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
class GetLoanUseCaseImplTest{
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
    fun `WHEN LoanViewModel getLoan EXPECT LoanEntity with id 1`() = runTest {
        val repository: LoansRepository = mock()
        whenever(repository.getLoan(1)).thenReturn(LoanHistoryResult.Success(loan))

        val useCase = GetLoanUseCaseImpl(repository)
        val actual = (useCase.execute(1) as LoanHistoryResult.Success).loan

        assertEquals(expected, actual)
    }
}