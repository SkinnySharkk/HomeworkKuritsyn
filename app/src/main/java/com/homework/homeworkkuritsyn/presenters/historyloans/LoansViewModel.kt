package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoansViewModel @Inject constructor(private val getLoansUseCase: GetLoansUseCase) :
    ViewModel() {

    private val _loans = MutableLiveData<List<LoanEntity>>()
    val loans: LiveData<List<LoanEntity>> get() = _loans

    init {
        viewModelScope.launch {
            _loans.value = getLoansUseCase.execute()
            Timber.v(_loans.value.toString())
        }
    }

    fun updateLoans() {
        viewModelScope.launch {
            _loans.value = getLoansUseCase.execute()
            Timber.v(_loans.value.toString())
        }
    }
}