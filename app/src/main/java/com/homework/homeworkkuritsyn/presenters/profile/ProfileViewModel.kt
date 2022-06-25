package com.homework.homeworkkuritsyn.presenters.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val deleteUserDataUseCase: DeleteUserDataUseCase
) : ViewModel() {
    fun deleteUserData() {
        viewModelScope.launch {
            deleteUserDataUseCase.execute()
        }
    }
}