package com.homework.homeworkkuritsyn.presenters.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
   private val signInUseCase: SignInUseCase
) : ViewModel() {
   fun login(name: String, password: String) {
      val authEntity = AuthEntity(name, password)
      viewModelScope.launch {
         signInUseCase.execute(authEntity)
      }
   }
   fun validData(name: String, password: String): Boolean {
      return name.isNotEmpty() && password.isNotEmpty()
   }
}