package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoansUseCase
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
            _uiState.value = LoansViewModelUiState.Success(getLoansUseCase.execute())
        }
    }
}

sealed interface LoansViewModelUiState {
    object Idle : LoansViewModelUiState
    object Loading : LoansViewModelUiState
    data class Success(val loans: List<LoanEntity>) : LoansViewModelUiState
}