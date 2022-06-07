package com.homework.homeworkkuritsyn.presenters

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import javax.inject.Inject

class LoginViewModel @Inject constructor(
   private val signInUseCase: SignInUseCase
) : ViewModel() {
   fun login(name: String, password: String) {
      signInUseCase.execute(name, password)
   }
}