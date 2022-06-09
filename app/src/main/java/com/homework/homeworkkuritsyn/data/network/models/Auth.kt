package com.homework.homeworkkuritsyn.data.network.models

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
//@JsonClass(generateAdapter = true)
data class Auth(
//    @Json(name = "name")
//    val name: String,
//    @Json(name = "password")
//    val password: String
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)
