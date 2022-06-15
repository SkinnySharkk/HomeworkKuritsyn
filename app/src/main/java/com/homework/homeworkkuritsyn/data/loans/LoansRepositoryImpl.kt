package com.homework.homeworkkuritsyn.data.loans

import com.homework.homeworkkuritsyn.data.converters.LoanListConverterToLoanEntityList
import com.homework.homeworkkuritsyn.data.converters.asEntities
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.loans.LoansRepository
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val networkShiftDataStore: NetworkShiftDataStore
) :
    LoansRepository {
    override suspend fun getAllLoans(): List<LoanEntity> {
        return LoanListConverterToLoanEntityList(networkShiftDataStore.getAllLoans()).asEntities()
    }

    override suspend fun getLoan(id: Int): LoanEntity {
        return LoanListConverterToLoanEntityList(listOf(networkShiftDataStore.getLoan(id = id))).asEntities()[0]
    }
}