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
    private val signUpUseCase: SignUpUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState
    fun register(
        login: String,
        password: String
    ) {
        val authEntity = AuthEntity(
            name = login,
            password = password
        )
        viewModelScope.launch(dispatcher) {
            _loginUiState.postValue(LoginUiState.Loading)
            when(val registerResult = signUpUseCase.execute(authEntity)) {
                is RegisterResult.Success -> {
                    _loginUiState.postValue(LoginUiState.Success)
                }
                is RegisterResult.HttpError -> {
                    _loginUiState.postValue(LoginUiState.Error(reason = registerResult.reason))
                }
                is RegisterResult.OtherError -> {
                    _loginUiState.postValue(LoginUiState.Error(reason = registerResult.reason))
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