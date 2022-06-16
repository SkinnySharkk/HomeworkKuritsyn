package com.homework.homeworkkuritsyn.data.authorized

import com.homework.homeworkkuritsyn.data.converters.*
import com.homework.homeworkkuritsyn.data.network.NetworkShiftDataStore
import com.homework.homeworkkuritsyn.data.sharedpreferences.SystemLocalSharedPreferencesDataStore
import com.homework.homeworkkuritsyn.domain.authorized.AuthResult
import com.homework.homeworkkuritsyn.domain.authorized.AuthorizedRepository
import com.homework.homeworkkuritsyn.domain.authorized.RegisterResult
import com.homework.homeworkkuritsyn.domain.entity.AuthEntity
import com.homework.homeworkkuritsyn.domain.entity.UserDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

class AuthorizedRepositoryImpl @Inject constructor(
    private val systemLocalSharedPreferencesDataStore: SystemLocalSharedPreferencesDataStore,
    private val networkShiftDataStore: NetworkShiftDataStore
) : AuthorizedRepository {
    override suspend fun isAuthorized(): Boolean = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.isAuthorized()
    }

    override suspend fun setAuthorized() = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.setAuthorized()
    }

    override fun getToken(): String =
        systemLocalSharedPreferencesDataStore.getToken()

    override suspend fun deleteUserData() {
        withContext(Dispatchers.IO) {
            systemLocalSharedPreferencesDataStore.deleteUserData()
        }
    }

    override suspend fun setToken(token: String) = withContext(Dispatchers.IO) {
        systemLocalSharedPreferencesDataStore.putToken(token)
    }

    override suspend fun signIn(authEntity: AuthEntity): AuthResult {
        val auth = AuthConverter(authEntity).asEntities()
        return withContext(Dispatchers.IO) {
            try {
                val token = networkShiftDataStore.signIn(auth = auth)
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

    override suspend fun signUp(authEntity: AuthEntity) : RegisterResult =
        withContext(Dispatchers.IO) {
            try {
                val auth = AuthConverter(authEntity).asEntities()
                networkShiftDataStore.signUp(auth = auth)
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


}