package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.di.DefaultDispatcher
import com.homework.homeworkkuritsyn.domain.authorized.RegisterResult
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState
    fun register(
        login: String,
        password: String
    ) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState.Loading
            when(val registerResult = signUpUseCase.execute(login = login, password = password)) {
                is RegisterResult.Success -> {
                    _loginUiState.value = LoginUiState.Success
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

    fun validData(
        login: String,
        password: String
    ): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }

    fun dropError() {
        _loginUiState.value = LoginUiState.Idle
    }
}