package com.homework.homeworkkuritsyn.data.historyloans

import com.homework.homeworkkuritsyn.data.converters.LoanListConverterToLoanEntityList
import com.homework.homeworkkuritsyn.data.converters.asEntities
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataSource
import com.homework.homeworkkuritsyn.di.IoDispatcher
import com.homework.homeworkkuritsyn.domain.historyloans.LoanHistoryResult
import com.homework.homeworkkuritsyn.domain.historyloans.LoansHistoryResult
import com.homework.homeworkkuritsyn.domain.historyloans.LoansRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val networkShiftDataSource: NetworkShiftDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) :
    LoansRepository {
    override suspend fun getAllLoans(): LoansHistoryResult = withContext(dispatcher) {
        try {
            val loans =
                LoanListConverterToLoanEntityList(networkShiftDataSource.getAllLoans()).asEntities()
            LoansHistoryResult.Success(loans)
        } catch (e: UnknownHostException) {
            Timber.v(e.localizedMessage)
            LoansHistoryResult.Error("Не получилось получить данные, проверьте интренет соединение")
        } catch (e: Exception) {
            Timber.v(e.localizedMessage)
            LoansHistoryResult.Error("Неизвестная ошибка")
        }
    }


    override suspend fun getLoan(id: Int): LoanHistoryResult = withContext(dispatcher) {
        try {
            val loan =
                LoanListConverterToLoanEntityList(listOf(networkShiftDataSource.getLoan(id = id))).asEntities()[0]
            LoanHistoryResult.Success(loan)
        } catch (e: UnknownHostException) {
            Timber.v(e.localizedMessage)
            LoanHistoryResult.Error("Не получилось получить данные, проверьте интренет соединение")
        } catch (e: Exception) {
            Timber.v(e.localizedMessage)
            LoanHistoryResult.Error("Неизвестная ошибка")
        }
    }
}