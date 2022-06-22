package com.homework.homeworkkuritsyn.data.authorized

import com.homework.homeworkkuritsyn.data.converters.AuthConverter
import com.homework.homeworkkuritsyn.data.converters.asEntities
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataSource
import com.homework.homeworkkuritsyn.data.sharedpreferences.SystemLocalSharedPreferencesDataSource
import com.homework.homeworkkuritsyn.di.IoDispatcher
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.authorized.RegisterResult
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class AuthorizedRepositoryImpl @Inject constructor(
    private val systemLocalSharedPreferencesDataSource: SystemLocalSharedPreferencesDataSource,
    private val networkShiftDataSource: NetworkShiftDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthorizedRepository {
    override fun isAuthorized(): Boolean =
        systemLocalSharedPreferencesDataSource.isAuthorized()

    override fun setAuthorized() =
        systemLocalSharedPreferencesDataSource.setAuthorizedIsTrue()

    override fun getToken(): String =
        systemLocalSharedPreferencesDataSource.getToken()

    override fun setToken(token: String) =
        systemLocalSharedPreferencesDataSource.putToken(token)

    override suspend fun signIn(authEntity: AuthEntity): AuthResult {
        val auth = AuthConverter(authEntity).asEntities()
        return withContext(dispatcher) {
            try {
                val token = networkShiftDataSource.signIn(auth = auth)
                setToken(token)
                AuthResult.Success
            } catch (httpException: HttpException) {
                when (httpException.code()) {
                    401 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Не авторизован")
                    }
                    403 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Запрещено")
                    }
                    404 -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Логин или пароль не найдены")
                    }
                    else -> {
                        Timber.v(httpException.localizedMessage)
                        AuthResult.HttpError("Ошибка")
                    }
                }
            } catch (e: Exception) {
                Timber.v(e.stackTraceToString())
                AuthResult.OtherError("Ошибка")
            }
        }
    }

    override suspend fun signUp(authEntity: AuthEntity): RegisterResult =
        withContext(dispatcher) {
            try {
                val auth = AuthConverter(authEntity).asEntities()
                networkShiftDataSource.signUp(auth = auth)
                RegisterResult.Success
            } catch (httpException: HttpException) {
                when (httpException.code()) {
                    400 -> {
                        Timber.v(httpException.localizedMessage)
                        RegisterResult.HttpError("Пользователь уже существует")
                    }
                    else -> {
                        Timber.v(httpException.localizedMessage)
                        RegisterResult.HttpError("Ошибка")
                    }
                }
            } catch (e: Exception) {
                Timber.v(e.stackTraceToString())
                RegisterResult.HttpError("Ошибка")
            }
        }

    override fun deleteUserData() {
        systemLocalSharedPreferencesDataSource.deleteUserData()
    }


}