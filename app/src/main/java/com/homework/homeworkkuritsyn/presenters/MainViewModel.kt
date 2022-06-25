package com.homework.homeworkkuritsyn.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.authorized.CheckIsAuthorizedUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkIsAuthorizedUseCase: CheckIsAuthorizedUseCase
) : ViewModel() {

    fun isAuthorized(): Boolean {
        return checkIsAuthorizedUseCase.execute()
    }
}