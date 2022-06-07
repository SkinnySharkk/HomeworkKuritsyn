package com.homework.homeworkkuritsyn.presenters

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.authorized.SignInUseCase
import javax.inject.Inject

class RegisterViewModel @Inject constructor(private val signInUseCase: SignInUseCase) :
    ViewModel() {
    // TODO: Implement the ViewModel
}