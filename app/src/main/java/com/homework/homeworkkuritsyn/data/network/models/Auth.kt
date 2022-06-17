package com.homework.homeworkkuritsyn.data.network.models

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
)
