package com.homework.homeworkkuritsyn.data.network.models

import com.homework.homeworkkuritsyn.domain.entity.UserRoleEntity
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserModel(
    val name: String,
    val role: UserRoleEntity
)
