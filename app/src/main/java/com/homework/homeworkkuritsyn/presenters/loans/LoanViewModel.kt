package com.homework.homeworkkuritsyn.presenters.loans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.loans.GetLoanUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanViewModel @Inject constructor(
    private val getLoanUseCase: GetLoanUseCase

    ) : ViewModel() {
    private val _loan = MutableLiveData<LoanEntity>()
    val loan: LiveData<LoanEntity> get() = _loan

    fun getLoan(id: Int) {
        viewModelScope.launch {
            _loan.value = getLoanUseCase.execute(id)
        }
    }
}