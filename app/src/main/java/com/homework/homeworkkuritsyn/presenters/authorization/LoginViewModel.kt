package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.di.AppModule
import com.homework.homeworkkuritsyn.di.DefaultDispatcher
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {
    private val _loginUiState = MutableLiveData<LoginUiState>(LoginUiState.Idle)
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun login(name: String, password: String) {
        val authEntity = AuthEntity(name, password)
        viewModelScope.launch(dispatcher) {
            _loginUiState.postValue(LoginUiState.Loading)
            when (val authResult = signInUseCase.execute(authEntity)) {
                is AuthResult.Success -> {
                    _loginUiState.postValue(LoginUiState.Success)
                }
                is AuthResult.HttpError -> {
                    _loginUiState.postValue(LoginUiState.Error(reason = authResult.reason))
                }
                is AuthResult.OtherError -> {
                    _loginUiState.postValue(LoginUiState.Error(reason = authResult.reason))
                }
            }
        }
    }

    fun validData(name: String, password: String): Boolean {
        return name.isNotEmpty() && password.isNotEmpty()
    }

    fun dropError() {
        _loginUiState.value = LoginUiState.Idle
    }
}

