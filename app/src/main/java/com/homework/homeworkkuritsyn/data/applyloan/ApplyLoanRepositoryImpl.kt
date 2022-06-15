package com.homework.homeworkkuritsyn.data.applyloan

import com.homework.homeworkkuritsyn.data.converters.*
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanRepository
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApplyLoanRepositoryImpl @Inject constructor(
    private val networkShiftDataStore: NetworkShiftDataStore
) : ApplyLoanRepository {
    override suspend fun getCondition(): LoanConditionsEntity =
        withContext(Dispatchers.IO) {
            LoanConditionsConverter(networkShiftDataStore.getLoanConditions()).asEntity()
        }

    override suspend fun applyLoan(loanRequestEntity: LoanRequestEntity): LoanEntity =
        withContext(Dispatchers.IO) {
            val loanRequestModel =
                LoanRequestEntityConverterToLoanRequest(loanRequestEntity).asModel()
            val loanModel = networkShiftDataStore.applyLoan(loanRequestModel)
            LoanListConverterToLoanEntityList(listOf(loanModel)).asEntities().first()
        }

}