package com.homework.homeworkkuritsyn.presenters.authorization

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.SignUpUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    fun register(
        login: String,
        password: String
    ) {
        val authEntity = AuthEntity(
            name = login,
            password = password
        )
        viewModelScope.launch {
            signUpUseCase.execute(authEntity)
        }
    }

    fun validData(
        login: String,
        password: String
    ): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }
}