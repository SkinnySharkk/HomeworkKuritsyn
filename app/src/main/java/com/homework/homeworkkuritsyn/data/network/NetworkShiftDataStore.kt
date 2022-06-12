package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.Auth
import com.homework.homeworkkuritsyn.data.network.models.Loan
import com.homework.homeworkkuritsyn.data.network.models.UserModel
import javax.inject.Inject

class NetworkShiftDataStore @Inject constructor(
    private val shiftService: ShiftService
) {
    suspend fun signIn(auth: Auth): String {
        return shiftService.login(auth)
    }
    suspend fun signUp(auth: Auth): UserModel {
       return shiftService.registration(auth)
    }
    suspend fun getAllLoans(): List<Loan> {
        return shiftService.getAllLoans()
    }
    suspend fun getLoan(id: Int): Loan {
        return shiftService.getLoan(id = id)
    }
}
