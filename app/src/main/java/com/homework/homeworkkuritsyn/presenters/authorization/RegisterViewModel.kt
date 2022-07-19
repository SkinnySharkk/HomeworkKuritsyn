package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.RegisterResult
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val inputAuthorizationValidator: InputAuthorizationValidator
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState
    fun register(
        login: String,
        password: String
    ) {
        if (validLoginPassword(login, password)) {
            viewModelScope.launch {
                _loginUiState.value = LoginUiState.Loading
                when (val registerResult =
                    signUpUseCase.execute(login = login, password = password)) {
                    is RegisterResult.Success -> {
                        login(login = login, password = password)
                    }
                    is RegisterResult.HttpError -> {
                        _loginUiState.value = LoginUiState.Error(reason = registerResult.reason)
                    }
                    is RegisterResult.OtherError -> {
                        _loginUiState.value = LoginUiState.Error(reason = registerResult.reason)
                    }
                }
            }
        }
    }

    private fun login(login: String, password: String) {
        viewModelScope.launch {
            when (val loginResult = signInUseCase.execute(login = login, password = password)) {
                is AuthResult.Success -> {
                    _loginUiState.value = LoginUiState.Success
                }
                is AuthResult.HttpError -> {
                    _loginUiState.value = LoginUiState.Error(reason = loginResult.reason)
                }
                is AuthResult.OtherError -> {
                    _loginUiState.value = LoginUiState.Error(reason = loginResult.reason)
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