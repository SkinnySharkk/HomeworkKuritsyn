package com.homework.homeworkkuritsyn.data.historyloans

import com.homework.homeworkkuritsyn.data.converters.LoanListConverterToLoanEntityList
import com.homework.homeworkkuritsyn.data.converters.asEntities
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.domain.historyloans.LoanHistoryResult
import com.homework.homeworkkuritsyn.domain.historyloans.LoansHistoryResult
import com.homework.homeworkkuritsyn.domain.historyloans.LoansRepository
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class LoansRepositoryImpl @Inject constructor(
    private val networkShiftDataStore: NetworkShiftDataStore
) :
    LoansRepository {
    override suspend fun getAllLoans(): LoansHistoryResult {
        return try {
            val loans =
                LoanListConverterToLoanEntityList(networkShiftDataStore.getAllLoans()).asEntities()
            LoansHistoryResult.Success(loans)
        } catch (e: UnknownHostException) {
            Timber.v(e.localizedMessage)
            LoansHistoryResult.Error("Не получилось получить данные, проверьте интренет соединение")
        } catch (e: Exception) {
            Timber.v(e.localizedMessage)
            LoansHistoryResult.Error("Неизвестная ошибка")
        }
    }

    override suspend fun getLoan(id: Int): LoanHistoryResult {
        return try {
            val loan =
                LoanListConverterToLoanEntityList(listOf(networkShiftDataStore.getLoan(id = id))).asEntities()[0]
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