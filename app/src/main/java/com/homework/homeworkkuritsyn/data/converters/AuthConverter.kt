package com.homework.homeworkkuritsyn.data.converters

import com.homework.homeworkkuritsyn.data.network.models.Auth
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity

data class AuthConverter(val authEntity: AuthEntity)
fun AuthConverter.asEntities(): Auth {
    return Auth(
        name = authEntity.name,
        password = authEntity.password
    )
}
