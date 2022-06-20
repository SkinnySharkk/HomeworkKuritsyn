package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCase
import com.homework.homeworkkuritsyn.domain.historyloans.LoansHistoryResult
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoansViewModel @Inject constructor(private val getLoansUseCase: GetLoansUseCase) :
    ViewModel() {
    private val _uiState = MutableLiveData<LoansViewModelUiState>(LoansViewModelUiState.Idle)
    val uiState: LiveData<LoansViewModelUiState> = _uiState

    init {
        viewModelScope.launch {
            updateLoans()
        }
    }

    fun updateLoans() {
        _uiState.value = LoansViewModelUiState.Loading
        viewModelScope.launch {
            when(val result = getLoansUseCase.execute()) {
                is LoansHistoryResult.Success -> {
                    _uiState.value = LoansViewModelUiState.Success(result.loans)
                }
                is LoansHistoryResult.Error -> {
                    _uiState.value = LoansViewModelUiState.Error(result.response)
                }
            }
        }
    }
}

sealed interface LoansViewModelUiState {
    object Idle : LoansViewModelUiState
    object Loading : LoansViewModelUiState
    data class Success(val loans: List<LoanEntity>) : LoansViewModelUiState
    data class Error(val response: String) : LoansViewModelUiState
}