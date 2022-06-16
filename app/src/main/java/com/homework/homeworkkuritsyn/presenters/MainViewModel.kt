package com.homework.homeworkkuritsyn.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkFirstStartUseCase: CheckFirstStartUseCase,
    private val deleteUserDataUseCase: DeleteUserDataUseCase
) : ViewModel() {

    fun checkIsFirstStart(): Boolean {
        return checkFirstStartUseCase.execute()
    }
    fun deleteUserData() {
        viewModelScope.launch {
            deleteUserDataUseCase.execute()
        }
    }
}