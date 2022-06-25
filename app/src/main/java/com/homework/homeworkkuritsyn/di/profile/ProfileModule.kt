package com.homework.homeworkkuritsyn.di.profile

import androidx.lifecycle.ViewModel
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCase
import com.homework.homeworkkuritsyn.domain.DeleteUserDataUseCaseImpl
import com.homework.homeworkkuritsyn.presenters.ViewModelKey
import com.homework.homeworkkuritsyn.presenters.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProfileModule {
    @Binds
    fun bindDeleteUserDataUseCase(
        deleteUserDataUseCaseImpl: DeleteUserDataUseCaseImpl
    ): DeleteUserDataUseCase

    @Binds
    @[IntoMap ViewModelKey(ProfileViewModel::class)]
    fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel
}