package com.homework.homeworkkuritsyn.presenters.applyloan

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.GetLoanConditionsUseCase
import com.homework.homeworkkuritsyn.domain.entity.EnumStateEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
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
import java.math.BigInteger

@ExperimentalCoroutinesApi
class ApplyLoanViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var loanRequest: LoanRequestEntity
    private lateinit var expectedLoan: LoanEntity
    private lateinit var expectedConditions: LoanConditionsEntity
    private lateinit var loan: LoanEntity
    private lateinit var conditions: LoanConditionsEntity

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        loanRequest = LoanRequestEntity(
            amount = BigInteger.valueOf(1000),
            percent = BigDecimal.TEN,
            lastName = "Mouse",
            firstName = "Mickey",
            period = BigInteger.valueOf(48),
            phoneNumber = "89257419586"
        )
        expectedLoan = LoanEntity(
            amount = BigDecimal.valueOf(1000),
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
            amount = BigDecimal.valueOf(1000),
            percent = BigDecimal.TEN,
            period = 48,
            date = "01.05.21",
            state = EnumStateEntity.REJECTED,
            phoneNumber = "89257419586",
            lastName = "Mouse",
            firstName = "Mickey",
            id = 8
        )

        conditions = LoanConditionsEntity(
            maxAmount = BigInteger.valueOf(1000),
            percent = BigDecimal.TEN,
            period = 48
        )
        expectedConditions = LoanConditionsEntity(
            maxAmount = BigInteger.valueOf(1000),
            percent = BigDecimal.TEN,
            period = 48
        )
    }

    @Test
    fun `WHEN ApplyLoanViewModelTest create loanConditions not null`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        val actual = applyLoanViewModel.loanConditions.value

        assertEquals(expectedConditions, actual)
    }

    @Test
    fun `WHEN setSum(percentSum = 50) EXPECTED 500`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        applyLoanViewModel.setSum(percentSum = 50)
        val actual = applyLoanViewModel.selectSum.value
        val expected = 500
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN getPercentOfAmount(500) EXPECTED 50`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        val actual = applyLoanViewModel.getPercentOfAmount("500")
        val expected = 50
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN validData(Mickey, Mouse, 89789564217, 12000) EXPECTED true`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        val actual = applyLoanViewModel.validData(
            "Mickey",
            "Mouse",
            "89789564217",
            "12000"
        )
        val expected = true
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN validData(, Mouse, 89789564217, 12000) EXPECTED false`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        val actual = applyLoanViewModel.validData(
            "",
            "Mouse",
            "89789564217",
            "12000"
        )
        val expected = false
        assertEquals(expected, actual)
    }

    @Test
    fun `WHEN applyLoan EXPECTED loanEntity`() = runTest {
        val applyLoanUseCase: ApplyLoanUseCase = mock()
        whenever(applyLoanUseCase.execute(loanRequest)).thenReturn(loan)
        val getLoanConditionsUseCase: GetLoanConditionsUseCase = mock()
        whenever(getLoanConditionsUseCase.execute()).thenReturn(expectedConditions)

        val applyLoanViewModel = ApplyLoanViewModel(
            applyLoanUseCase = applyLoanUseCase,
            getLoanConditionsUseCase = getLoanConditionsUseCase
        )

        applyLoanViewModel.applyLoan(
            firstName = "Mickey",
            lastName = "Mouse",
            phone = "89257419586",
            sum = BigInteger.valueOf(1000)
        )
        val actual = applyLoanViewModel.loanEntity.value

        assertEquals(expectedLoan, actual)
    }
}