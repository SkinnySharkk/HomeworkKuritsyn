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
    private lateinit var expected: ApplyLoanResult.Success

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
        expected = ApplyLoanResult.Success
    }

    @Test
    fun `WHEN execute EXPECTED LoanConditions`() = runTest {
        val useCase: ApplyLoanUseCase = mock()
        whenever(useCase.execute(loanRequest)).thenReturn(ApplyLoanResult.Success)

        val actual = (useCase.execute(loanRequest) as ApplyLoanResult.Success)

        assertEquals(expected, actual)
    }
}