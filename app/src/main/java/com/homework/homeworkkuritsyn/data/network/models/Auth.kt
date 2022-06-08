package com.homework.homeworkkuritsyn.data.network.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Auth(
    @Json(name = "name")
    val name: String,
    @Json(name = "password")
    val password: String
)
