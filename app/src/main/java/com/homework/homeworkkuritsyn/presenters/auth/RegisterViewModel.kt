package com.homework.homeworkkuritsyn.presenters.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    fun register(name: String, password: String) {
        val authEntity = AuthEntity(name, password)
        viewModelScope.launch {
            signUpUseCase.execute(authEntity)
        }
    }
    fun validData(name: String, password: String): Boolean {
        return name.isNotEmpty() && password.isNotEmpty()
    }
}