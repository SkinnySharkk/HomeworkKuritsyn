package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val inputAuthorizationValidator: InputAuthorizationValidator
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun login(name: String, password: String) {
        if (validLoginPassword(name, password)) {
            _loginUiState.value = LoginUiState.Loading
            viewModelScope.launch {
                when (val authResult = signInUseCase.execute(login = name, password = password)) {
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
    }

    private fun validLoginPassword(name: String, password: String): Boolean {
        val loginIsValid = inputAuthorizationValidator.isCorrectLogin(name)
        val passwordIsValid = inputAuthorizationValidator.isCorrectPassword(password)
        if (loginIsValid && passwordIsValid) {
            return true
        } else if (!loginIsValid && !passwordIsValid) {
            _loginUiState.value =
                LoginUiState.ErrorLogin
            _loginUiState.value =
                LoginUiState.ErrorPassword
            return false
        } else if (!loginIsValid) {
            _loginUiState.value =
                LoginUiState.ErrorLogin
            return false
        } else {
            _loginUiState.value =
                LoginUiState.ErrorPassword
            return false
        }
    }

    fun dropError() {
        _loginUiState.value = LoginUiState.Idle
    }
}

