package com.homework.homeworkkuritsyn.presenters.historyloans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.historyloans.GetLoanUseCase
import com.homework.homeworkkuritsyn.domain.historyloans.LoanHistoryResult
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class LoanViewModel @Inject constructor(
    private val getLoanUseCase: GetLoanUseCase

) : ViewModel() {
    private val _uiState = MutableLiveData<LoanVieModelUiState>()
    val uiState: LiveData<LoanVieModelUiState> = _uiState
    fun getLoan(id: Int) {
        _uiState.value = LoanVieModelUiState.Loading
        viewModelScope.launch {
            val result = getLoanUseCase.execute(id)
            Timber.v(result.toString())
            when (result) {
                is LoanHistoryResult.Success -> {
                    _uiState.value = LoanVieModelUiState.Success(result.loan)
                }
                is LoanHistoryResult.Error -> {
                    _uiState.value = LoanVieModelUiState.Error(result.response)
                }
            }
        }
    }
}

sealed interface LoanVieModelUiState {
    object Loading : LoanVieModelUiState
    data class Success(val loan: LoanEntity) : LoanVieModelUiState
    data class Error(val response: String) : LoanVieModelUiState
}