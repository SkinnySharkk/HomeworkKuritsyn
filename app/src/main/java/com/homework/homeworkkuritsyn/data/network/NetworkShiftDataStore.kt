package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.Auth
import com.homework.homeworkkuritsyn.data.network.models.UserModel
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class NetworkShiftDataStore @Inject constructor(
    private val shiftService: ShiftService
) {
    suspend fun signIn(auth: Auth) {
            val body = MultipartBody.Part.createFormData(
                "filename",
                "user",
                RequestBody.create("application/json".toMediaType(), auth)
            )
        shiftService.login(body)
    }
    suspend fun signUp(auth: Auth): UserModel {
       return shiftService.registration(auth)
    }
}
