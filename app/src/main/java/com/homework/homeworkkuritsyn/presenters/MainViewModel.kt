package com.homework.homeworkkuritsyn.presenters

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.authorized.CheckFirstStartUseCase
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val checkFirstStartUseCase: CheckFirstStartUseCase
) : ViewModel() {

    fun checkIsFirstStart(): Boolean {
        return checkFirstStartUseCase.execute()
    }
}