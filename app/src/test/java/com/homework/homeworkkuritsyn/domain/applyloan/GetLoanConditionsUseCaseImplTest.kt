package com.homework.homeworkkuritsyn.domain.applyloan

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
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
class GetLoanConditionsUseCaseImplTest {
    private lateinit var conditions: LoanConditionsEntity
    private lateinit var expected: LoanConditionsEntity

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        conditions = LoanConditionsEntity(
            maxAmount = BigInteger.TEN,
            percent = BigDecimal.TEN,
            period = 48
        )
        expected = LoanConditionsEntity(
            maxAmount = BigInteger.TEN,
            percent = BigDecimal.TEN,
            period = 48
        )
    }

    @Test
    fun `WHEN execute EXPECTED LoanConditions`() = runTest {
        val useCase: GetLoanConditionsUseCase = mock()
        whenever(useCase.execute()).thenReturn(LoanConditionsResult.Success(conditions))

        val actual = (useCase.execute() as LoanConditionsResult.Success).conditions

        assertEquals(expected, actual)
    }
}