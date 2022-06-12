package com.homework.homeworkkuritsyn.data.loans

import com.homework.homeworkkuritsyn.data.converters.LoanConverter
import com.homework.homeworkkuritsyn.data.converters.asModel
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.loans.LoansRepository
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val networkShiftDataStore: NetworkShiftDataStore
) :
    LoansRepository {
    override suspend fun getAllLoans(): List<LoanEntity> {
        return LoanConverter(networkShiftDataStore.getAllLoans()).asModel()
    }

    override suspend fun getLoan(id: Int): LoanEntity {
        return LoanConverter(listOf(networkShiftDataStore.getLoan(id = id))).asModel()[0]
    }
}