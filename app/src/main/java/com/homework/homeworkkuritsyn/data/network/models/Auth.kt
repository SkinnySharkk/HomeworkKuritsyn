package com.homework.homeworkkuritsyn.data.network.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
data class Auth(
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)
