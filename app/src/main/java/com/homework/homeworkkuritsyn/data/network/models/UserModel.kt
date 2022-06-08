package com.homework.homeworkkuritsyn.data.network.models

import com.homework.homeworkkuritsyn.domain.entity.UserRole

data class UserModel(
    val name: String,
    val role: UserRole
)
