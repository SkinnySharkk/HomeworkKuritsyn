package com.homework.homeworkkuritsyn.presenters.authorization

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Success : LoginUiState
    object Loading : LoginUiState
    data class Error(val reason: String) : LoginUiState
    object ErrorLogin : LoginUiState
    object ErrorPassword : LoginUiState
}