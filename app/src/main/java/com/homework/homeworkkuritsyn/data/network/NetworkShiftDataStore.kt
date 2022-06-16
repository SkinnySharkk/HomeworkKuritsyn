package com.homework.homeworkkuritsyn.data.network

import com.homework.homeworkkuritsyn.data.network.models.*
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import javax.inject.Inject

class NetworkShiftDataStore @Inject constructor(
    private val shiftService: ShiftService
) {
    suspend fun signIn(auth: Auth): String {
        return shiftService.login(auth)
    }
    suspend fun signUp(auth: Auth){
        shiftService.registration(auth)
    }
    suspend fun getAllLoans(): List<Loan> {
        return shiftService.getAllLoans()
    }
    suspend fun getLoan(id: Int): Loan {
        return shiftService.getLoan(id = id)
    }
    suspend fun getLoanConditions(): LoanConditions {
        return shiftService.getConditions()
    }
    suspend fun applyLoan(loanRequestModel: LoanRequest): Loan {
        return shiftService.createLoans(loanRequestModel)
    }
}
