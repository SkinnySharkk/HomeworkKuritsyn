package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val inputAuthorizationValidator: InputAuthorizationValidator
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun login(name: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
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

    fun validData(name: String, password: String): Boolean {
        Timber.v("password = ${inputAuthorizationValidator.isCorrectPassword(password = password)}")
//        Timber.v("login = ${inputAuthorizationValidator.isCorrectLogin(login = name)}")
        when(val result = inputAuthorizationValidator.isCorrectLogin(login = name)) {
            is InputAuthorizationValidatorResult.IsCorrect -> {
                Timber.v("login = IsCorrect")
            }
            is InputAuthorizationValidatorResult.IsNotCorrect -> {
                Timber.v("login = IsNotCorrect, ${result.reason}")
            }
            is InputAuthorizationValidatorResult.IsEmpty -> {
                Timber.v("login = IsEmpty")
            }
        }

        return name.isNotEmpty() && password.isNotEmpty()
    }

    fun dropError() {
        _loginUiState.value = LoginUiState.Idle
    }
}

