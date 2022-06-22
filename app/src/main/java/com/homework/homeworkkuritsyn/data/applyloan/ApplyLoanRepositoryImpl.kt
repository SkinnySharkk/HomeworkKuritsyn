package com.homework.homeworkkuritsyn.data.applyloan

import com.homework.homeworkkuritsyn.data.converters.*
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.di.IoDispatcher
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanRepository
import com.homework.homeworkkuritsyn.domain.applyloan.ApplyLoanResult
import com.homework.homeworkkuritsyn.domain.applyloan.LoanConditionsResult
import com.homework.homeworkkuritsyn.domain.entity.LoanConditionsEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanEntity
import com.homework.homeworkkuritsyn.domain.entity.LoanRequestEntity
import com.homework.homeworkkuritsyn.domain.historyloans.LoanHistoryResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.net.UnknownHostException
import javax.inject.Inject

class ApplyLoanRepositoryImpl @Inject constructor(
    private val networkShiftDataStore: NetworkShiftDataStore,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ApplyLoanRepository {
    override suspend fun getCondition(): LoanConditionsResult =
        withContext(dispatcher) {
            try {
                val condition = LoanConditionsConverter(networkShiftDataStore.getLoanConditions()).asEntity()
                LoanConditionsResult.Success(condition)
            } catch (e: UnknownHostException) {
                Timber.v(e.localizedMessage)
                LoanConditionsResult.Error("Не получилось получить данные, проверьте интренет соединение")
            } catch (e: Exception) {
                Timber.v(e.localizedMessage)
                LoanConditionsResult.Error("Неизвестная ошибка")
            }
        }

    override suspend fun applyLoan(loanRequestEntity: LoanRequestEntity): ApplyLoanResult =
        withContext(dispatcher) {
            try {
                val loanRequestModel =
                    LoanRequestEntityConverterToLoanRequest(loanRequestEntity).asModel()
                networkShiftDataStore.applyLoan(loanRequestModel)
                ApplyLoanResult.Success
            }  catch (e: UnknownHostException) {
                Timber.v(e.localizedMessage)
                ApplyLoanResult.Error("Не удалось оформить займ, проверьте интернет соединение")
            } catch (e: Exception) {
                Timber.v(e.localizedMessage)
                ApplyLoanResult.Error("Неизвестная ошибка")
            }
        }

}