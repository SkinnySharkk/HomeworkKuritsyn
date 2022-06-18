package com.homework.homeworkkuritsyn.domain.applyloan

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.math.BigInteger

@ExperimentalCoroutinesApi
class ApplyLoanUseCaseImplTest {
    private lateinit var loanRequest: LoanRequestEntity
    private lateinit var expected: LoanEntity
    private lateinit var loan: LoanEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        loanRequest = LoanRequestEntity(
            amount = BigInteger.TEN,
            percent = BigDecimal.TEN,
            lastName = "Mouse",
            firstName = "Mickey",
            period = BigInteger.valueOf(48),
            phoneNumber = "89257419586"
        )
        expected = LoanEntity(
            amount = BigDecimal.TEN,
            percent = BigDecimal.TEN,
            period = 48,
            date = "01.05.21",
            state = EnumStateEntity.REJECTED,
            phoneNumber = "89257419586",
            lastName = "Mouse",
            firstName = "Mickey",
            id = 8
        )
        loan = LoanEntity(
            amount = BigDecimal.TEN,
            percent = BigDecimal.TEN,
            period = 48,
            date = "01.05.21",
            state = EnumStateEntity.REJECTED,
            phoneNumber = "89257419586",
            lastName = "Mouse",
            firstName = "Mickey",
            id = 8
        )
    }

    @Test
    fun `WHEN execute EXPECTED LoanConditions`() = runTest {
        val useCase: ApplyLoanUseCase = mock()
        whenever(useCase.execute(loanRequest)).thenReturn(loan)

        val actual = useCase.execute(loanRequest)

        assertEquals(expected, actual)
    }
}