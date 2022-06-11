package com.homework.homeworkkuritsyn.presenters.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun login(name: String, password: String) {
        val authEntity = AuthEntity(name, password)
        viewModelScope.launch {
            when (val authResult = signInUseCase.execute(authEntity)) {
                is AuthResult.Success -> {
                    _loginUiState.value = LoginUiState.Success
                }
                is AuthResult.HttpError -> {
                    _loginUiState.value = LoginUiState.Error(reason = authResult.reason)
                }
                is AuthResult.OtherError -> {
                    _loginUiState.value = LoginUiState.Error(reason = authResult.reason)
                }
            }
        }
    }

    fun validData(name: String, password: String): Boolean {
        return name.isNotEmpty() && password.isNotEmpty()
    }
}

sealed interface LoginUiState {
    object Idle : LoginUiState
    object Success : LoginUiState
    data class Error(val reason: String) : LoginUiState
}