package com.homework.homeworkkuritsyn.presenters.applyloan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanResult
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.GetLoanConditionsUseCase
import com.homework.homeworkkuritsyn.domain.applyloan.LoanConditionsResult
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

    private val _uiState = MutableLiveData<ApplyLoanViewModelUiState>()
    val uiState: LiveData<ApplyLoanViewModelUiState> = _uiState

    private val _loanEntity = MutableLiveData<LoanEntity>()
    val loanEntity: LiveData<LoanEntity> = _loanEntity

    init {
        _uiState.value = ApplyLoanViewModelUiState.Loading
        viewModelScope.launch {
            when(val result = getLoanConditionsUseCase.execute()) {
               is LoanConditionsResult.Success -> {
                   _loanConditions.value = result.conditions
                   _uiState.value = ApplyLoanViewModelUiState.SuccessConditions(result.conditions)
               }
               is LoanConditionsResult.Error -> {
                   _uiState.value = ApplyLoanViewModelUiState.Error(result.response)
               }
           }
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
        val sum = BigInteger(inputText.toString())
        val onePercent = _loanConditions.value?.maxAmount?.div(BigInteger.valueOf(100L))!!
        return sum.div(onePercent).toInt()
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
            when(val result = applyLoanUseCase.execute(loan)) {
                is ApplyLoanResult.Success -> {
                    _uiState.value = ApplyLoanState.SuccessApply
                }
                is ApplyLoanResult.Error -> {
                    _uiState.value = ApplyLoanViewModelUiState.Error(result.response)
                }
            }
        }
    }

    fun validData(firstName: String, lastName: String, phone: String, sum: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty() && sum.isNotEmpty()
    }
}

sealed interface ApplyLoanViewModelUiState {
    object Loading : ApplyLoanViewModelUiState
    data class SuccessConditions(val loanConditions: LoanConditionsEntity) : ApplyLoanViewModelUiState
    data class Error(val response: String) : ApplyLoanViewModelUiState
}
sealed interface ApplyLoanState: ApplyLoanViewModelUiState {
    object SuccessApply : ApplyLoanViewModelUiState
}