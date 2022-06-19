package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoanUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoanViewModel @Inject constructor(
    private val getLoanUseCase: GetLoanUseCase

) : ViewModel() {
    private val _uiState = MutableLiveData<LoanVieModelUiState>()
    val uiState: LiveData<LoanVieModelUiState> = _uiState
    fun getLoan(id: Int) {
        _uiState.value = LoanVieModelUiState.Loading
        viewModelScope.launch {
            _uiState.value = LoanVieModelUiState.Success(getLoanUseCase.execute(id))
        }
    }
}

sealed interface LoanVieModelUiState {
    object Loading : LoanVieModelUiState
    data class Success(val loan: LoanEntity) : LoanVieModelUiState
}