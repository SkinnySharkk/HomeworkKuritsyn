package com.homework.homeworkkuritsyn.presenters.applyloan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.GetLoanConditionsUseCase
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import kotlinx.coroutines.launch
import java.math.BigInteger
import javax.inject.Inject

class ApplyLoanViewModel @Inject constructor(
    private val getLoanConditionsUseCase: GetLoanConditionsUseCase,
    private val applyLoanUseCase: ApplyLoanUseCase
) : ViewModel() {
    private val _selectSum = MutableLiveData<Int>()
    val selectSum: LiveData<Int> get() = _selectSum
    private val _loanConditions = MutableLiveData<LoanConditionsEntity>()
    val loanConditions: LiveData<LoanConditionsEntity> get() = _loanConditions
    private val _loanEntity = MutableLiveData<LoanEntity>()
    val loanEntity: LiveData<LoanEntity> = _loanEntity

    init {
        viewModelScope.launch {
            _loanConditions.value = getLoanConditionsUseCase.execute()
        }
    }

    fun setSum(percentSum: Int) {
        _selectSum.value =
            calcPartSum(percentSum, _loanConditions.value?.maxAmount ?: BigInteger.ONE)
    }

    private fun calcPartSum(percent: Int, sum: BigInteger): Int {
        return sum.div(BigInteger.valueOf(100L)).multiply(BigInteger.valueOf(percent.toLong()))
            .toInt()
    }

    fun getPercentOfAmount(inputText: CharSequence): Int {
        val sum = Integer.parseInt(inputText.toString())
        val onePercent = _loanConditions.value?.maxAmount?.div(BigInteger.valueOf(100L))!!
        return BigInteger.valueOf(sum.toLong()).div(onePercent).toInt()
    }

    fun applyLoan(sum: BigInteger, firstName: String, lastName: String, phone: String) {
        viewModelScope.launch {
            val loan = LoanRequestEntity(
                amount = sum,
                percent = _loanConditions.value?.percent!!,
                period = BigInteger.valueOf(_loanConditions.value?.period?.toLong()!!),
                phoneNumber = phone,
                firstName = firstName,
                lastName = lastName
            )
            _loanEntity.value = applyLoanUseCase.execute(loan)
        }
    }

    fun validData(firstName: String, lastName: String, phone: String, sum: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() && sum.isNotEmpty()
    }
}